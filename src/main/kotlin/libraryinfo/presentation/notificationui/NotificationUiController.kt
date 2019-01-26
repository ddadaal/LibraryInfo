package libraryinfo.presentation.notificationui

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTreeTableColumn
import com.jfoenix.controls.JFXTreeTableView
import com.jfoenix.controls.RecursiveTreeItem
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import libraryinfo.appservice.auth.AuthAppService
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.util.DateHelper

class NotificationUiController : UiController {

    override fun load(): UiElement {
        return doLoad("/fxml/notificationui/NotificationUi.fxml")
    }

    lateinit var selectAllButton: JFXButton
    lateinit var notificationTable: JFXTreeTableView<NotificationModel>
    lateinit var tableDateColumn: JFXTreeTableColumn<NotificationModel, String>
    lateinit var tableIdColumn: JFXTreeTableColumn<NotificationModel, String>
    lateinit var readButton: JFXButton
    lateinit var tableSenderColumn: JFXTreeTableColumn<NotificationModel, String>
    lateinit var tableContentColumn: JFXTreeTableColumn<NotificationModel, String>

    var notificationModels = FXCollections.observableArrayList<NotificationModel>()!!



    val selected: NotificationModel?
        get() = notificationTable.selectionModel.selectedItem?.value


    fun initialize() {
        initNotifyItem()
    }

    fun updateItems() {
        notificationModels.clear()
        notificationModels.addAll(AuthAppService.currentUser!!.unreadNotification.map { NotificationModel(it) })
    }

    fun initNotifyItem() {
        tableDateColumn.setCellValueFactory { SimpleStringProperty(DateHelper.fromDate(it.value.value.notification.date)) }
        tableIdColumn.setCellValueFactory { SimpleStringProperty(it.value.value.notification.id.short) }
        tableSenderColumn.setCellValueFactory { SimpleStringProperty(it.value.value.notification.sender.name) }
        tableContentColumn.setCellValueFactory { SimpleStringProperty(it.value.value.notification.content) }
        val root = RecursiveTreeItem(notificationModels) { it.children }

        notificationTable.root = root
        notificationTable.isShowRoot = false
        notificationTable.setOnMouseClicked { event ->
            if (event.clickCount == 2) {
                onReadButtonClicked(null)
            }
        }
    }


    fun onRefreshButtonClicked(actionEvent: ActionEvent) {
        Globals.framework.refreshNotificationStatus()
    }

    private fun showNotSelectedDialog() {
        PromptDialogHelper.start("请选择一条通知！", "请选择一条通知！")
            .addCloseButton("好", "CHECK", null)
            .createAndShow()
    }

    fun onReadButtonClicked(actionEvent: ActionEvent?) {
        val model = selected
        if (model != null) {

            val ui = NotificationDetailUiController().load()

            val controller = ui.getController<NotificationDetailUiController>()

            controller.fillContent(model.notification)


            val dialog = PromptDialogHelper.start("消息详细内容", "你选择了通知")
                .setContent(ui.component)
                .create()

            controller.exitCallback = {
                dialog.close()
                this.updateItems()
            }

            dialog.show()
        } else {
            showNotSelectedDialog()
        }


    }

}
