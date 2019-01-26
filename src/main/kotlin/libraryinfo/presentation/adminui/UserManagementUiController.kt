package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXTextField
import com.jfoenix.controls.JFXTreeTableColumn
import com.jfoenix.controls.JFXTreeTableView
import com.jfoenix.controls.RecursiveTreeItem
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.SelectionMode
import javafx.scene.input.KeyCode
import libraryinfo.appservice.usermanagement.UserManagementAppServiceFactory
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.systeminit.SystemInitDomainService
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.ReadOnlyPairTableHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.BorrowRecordTableUiController
import libraryinfo.repository.user.UserRepository

class UserModel(val user: User) : RecursiveTreeObject<UserModel>() {
    val idProperty = SimpleStringProperty(user.id.toString())
    val nameProperty = SimpleStringProperty(user.name)
    val usernameProperty = SimpleStringProperty(user.username)
    val typeProperty = SimpleStringProperty(user.type.name)
}

class UserManagementUiController : UiController {

    lateinit var tableUsers: JFXTreeTableView<UserModel>
    lateinit var tcId: JFXTreeTableColumn<UserModel, String>
    lateinit var tcName: JFXTreeTableColumn<UserModel, String>
    lateinit var tcType: JFXTreeTableColumn<UserModel, String>
    lateinit var tcUsername: JFXTreeTableColumn<UserModel, String>
    lateinit var tfSearch: JFXTextField

    private val users: ObservableList<UserModel> = FXCollections.observableArrayList()
    private val userManagementService = UserManagementAppServiceFactory.service

    private val selected: User?
        get() {
            return try {
                tableUsers.selectionModel.selectedItem.value.user

            } catch (e: Exception) {
                PromptDialogHelper("未选择！", "请先选择一位用户！")
                    .addCloseButton("好", "CHECK", null)
                    .createAndShow()
                null
            }

        }

    override fun load(): UiElement {
        return doLoad("/fxml/adminui/UserManagement.fxml")
    }

    fun onAddButtonClicked() {
        UserAddDialog({ updateItems() }, "添加用户", "").createAndShow()
    }

    fun onModifyButtonClicked() {
        val user = selected
        if (user != null) {
            UserInfoEditingDialog(
                user,
                { updateItems() },
                "修改用户信息", 
                "",
                true
            )
                .createAndShow()
        }
    }

    fun onReportButtonClicked() {
        val user = selected
        if (user != null) {
            val element = BorrowRecordTableUiController().load()
            element.getController<BorrowRecordTableUiController>().propsUser = user
            PromptDialogHelper("借书记录", "")
                    .setContent(element.component)
                    .createAndShow()
        }
    }

    fun onPaymentButtonClicked() {
        val user = selected
        if (user != null) {
            var content = ""
            print(user.id)
            val report = userManagementService.generatePenaltyPayment(user.id)
            when {
                report.records.isEmpty() -> content += "无逾期记录"
                else -> {
                    report.records.forEach {
                        content +=
                                "${it.borrowDate} 所借书 ${it.bookId} 已逾期${it.day}天\r\n\r\n"
                    }
                    content += "共收费${report.fee}元"
                }
            }
            PromptDialogHelper("逾期费用", content)
                    .createAndShow()
        }
    }



    fun initialize() {
        initTable()
        updateItems()
    }

    private fun updateItems() {
        val query = tfSearch.text
        users.clear()
        UserRepository.data
            .filter { it != SystemInitDomainService.SYS_USER && (it.name.contains(query) || it.username.contains(query)) }
            .forEach { users.add(UserModel(it)) }
    }

    private fun initTable() {
        tfSearch.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                updateItems()
            }
        }
        tcId.setCellValueFactory { SimpleStringProperty(it.value.value.idProperty.get().substring(0, 6)) }
        tcName.setCellValueFactory { it.value.value.nameProperty }
        tcUsername.setCellValueFactory { it.value.value.usernameProperty }
        tcType.setCellValueFactory { it.value.value.typeProperty }

        val root = RecursiveTreeItem<UserModel>(users) { it.children }

        tableUsers.root = root
        tableUsers.isShowRoot = false
        tableUsers.selectionModel.selectionMode = SelectionMode.SINGLE
    }
}