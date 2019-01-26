package libraryinfo.presentation.userui

import com.jfoenix.controls.JFXTreeTableColumn
import com.jfoenix.controls.JFXTreeTableView
import com.jfoenix.controls.RecursiveTreeItem
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.SelectionMode
import libraryinfo.appservice.auth.AuthAppService
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.book.BookRepository
import libraryinfo.util.DateHelper
import libraryinfo.vo.borrowrecord.BorrowRecordVo

class MyBookModel(
        var instance: BookInstance,
        var record: BorrowRecordVo
) : RecursiveTreeObject<MyBookModel>() {
    val instanceIdProperty = SimpleObjectProperty(instance.id)
    val nameProperty = SimpleStringProperty(instance.book.name)
    val categoryProperty = SimpleStringProperty(instance.book.category)
    val borrowTimeProperty = SimpleObjectProperty(record.borrowTime)
    val returnDeadlineProperty = SimpleObjectProperty(record.borrowTime + record.duration)
}

class BorrowRecordTableUiController : UiController {

    lateinit var tableBooks: JFXTreeTableView<MyBookModel>
    lateinit var tcInstanceId: JFXTreeTableColumn<MyBookModel, String>
    lateinit var tcName: JFXTreeTableColumn<MyBookModel, String>
    lateinit var tcCategory: JFXTreeTableColumn<MyBookModel, String>
    lateinit var tcBorrowTime: JFXTreeTableColumn<MyBookModel, String>
    lateinit var tcReturnDeadline: JFXTreeTableColumn<MyBookModel, String>

    var instances = FXCollections.observableArrayList<MyBookModel>()

    var propsUser: User? = null
        set(value) {
            field = value
            updateItems()
        }

    val user: User
        get() = propsUser ?: AuthAppService.currentUser!!

    val selected: MyBookModel?
        get() {
            return try {
                tableBooks.selectionModel.selectedItem.value

            } catch (e: Exception) {
                PromptDialogHelper("未选择！", "请先选择一本书！")
                        .addCloseButton("好", "CHECK", null)
                        .createAndShow()
                null
            }

        }

    override fun load(): UiElement {
        return doLoad("/fxml/userui/BorrowRecordTable.fxml")
    }

    fun initialize() {
        initTable()
        updateItems()
    }

    fun updateItems() {
        when (propsUser) {
            null -> {
                val searchResult = user.ownedBookInstances
                instances.clear()
                instances.addAll(searchResult.map { b ->
                    MyBookModel(b, user.borrowRecords.find { r ->
                        r.id == b.recordId
                    }!!)
                })
            }
            else -> {
                instances.clear()
                instances.addAll(user.borrowRecords.map {
                    MyBookModel(
                            BookRepository.data.find { b -> b.instances.find { i -> i.id == it.bookInstanceId } != null }
                            !!.instances.find { i -> i.id == it.bookInstanceId }!!
                            , it)
                })
            }
        }
    }


    private fun initTable() {

        tcInstanceId.setCellValueFactory { SimpleStringProperty(it.value.value.instanceIdProperty.get().short) }
        tcName.setCellValueFactory { it.value.value.nameProperty }
        tcCategory.setCellValueFactory { it.value.value.categoryProperty }
        tcBorrowTime.setCellValueFactory { SimpleStringProperty(DateHelper.fromDate(it.value.value.borrowTimeProperty.get())) }
        tcReturnDeadline.setCellValueFactory { SimpleStringProperty(DateHelper.fromDate(it.value.value.returnDeadlineProperty.get())) }

        val root = RecursiveTreeItem<MyBookModel>(instances) { it.children }
        tableBooks.root = root
        tableBooks.isShowRoot = false
        tableBooks.selectionModel.selectionMode = SelectionMode.SINGLE
    }


}