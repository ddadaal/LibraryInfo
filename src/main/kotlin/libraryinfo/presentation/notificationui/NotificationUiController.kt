package libraryinfo.presentation.notificationui

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTreeTableColumn
import com.jfoenix.controls.JFXTreeTableView
import com.jfoenix.controls.RecursiveTreeItem
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import libraryinfo.presentation.internal.*
import libraryinfo.repository.notification.NotificationRepository
import libraryinfo.util.DateHelper

class NotificationUiController : UiController {

    lateinit var selectAllButton: JFXButton
    lateinit var notificationTable: JFXTreeTableView<NotificationModel>
    lateinit var tableDateColumn: JFXTreeTableColumn<NotificationModel, String>
    lateinit var tableTypeColumn: JFXTreeTableColumn<NotificationModel, String>
    lateinit var tableIdColumn: JFXTreeTableColumn<NotificationModel, String>
    lateinit var readButton: JFXButton
    lateinit var tableSenderColumn: JFXTreeTableColumn<NotificationModel, String>

    var notificationModels = FXCollections.observableArrayList<NotificationModel>()

    val selected: NotificationModel?
        get() = notificationTable.selectionModel.selectedItem?.value

    fun initialize() {
        initNotifyItem()
    }

    fun updateItems(): Int {
        notificationModels.clear()
        notificationModels.addAll(NotificationRepository.update(Globals.currentUser.id).map { NotificationModel(it) })
        return notificationModels.size
    }

    fun initNotifyItem() {
        tableDateColumn.setCellValueFactory { SimpleStringProperty(DateHelper.fromDate(it.value.value.notification.date)) }
        tableIdColumn.setCellValueFactory { SimpleStringProperty(it.value.value.notification.id.toString()) }
        tableSenderColumn.setCellValueFactory { SimpleStringProperty(it.value.value.notification.senderId) }

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
        Globals.frameworkUiController.refreshNotificationStatus()
    }

    fun showNotSelectedDialog() {
        PromptDialogHelper.start("请选择一条通知！", "请选择一条通知！")
            .addCloseButton("好", "CHECK", null)
            .createAndShow()
    }

    fun deleteItem(index: Int) {
        updateItems()
    }

    fun onReadButtonClicked(actionEvent: ActionEvent?) {
        val model = selected
        if (model != null) {
            val uiElementPackage = NotificationDetailUiController().load()
            val controller = uiElementPackage.getController<NotificationDetailUiController>()
            controller.fillContent(model.notification)

            controller.exitCallback = { this.updateItems() }
            PromptDialogHelper.start("消息详细内容", "你选择了通知")
                .setContent(uiElementPackage.component)
                .createAndShow()
        } else {
            showNotSelectedDialog()
        }


    }

    /**
     * Loads the controller.
     *
     * @return external loaded ui controller and component
     */
    override fun load(): UiElement {
        return UiLoader<NotificationUiController>("/fxml/notificationui/NotificationUi.fxml").loadAndGetElement()
    }
}
