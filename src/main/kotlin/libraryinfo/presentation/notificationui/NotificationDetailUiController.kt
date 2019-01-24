package libraryinfo.presentation.notificationui

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextArea
import com.jfoenix.controls.JFXTextField
import javafx.event.ActionEvent
import libraryinfo.entity.notification.Notification
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.internal.UiLoader
import libraryinfo.util.DateHelper

class NotificationDetailUiController : UiController {

    lateinit var tfId: JFXTextField
    lateinit var tfSender: JFXTextField
    lateinit var tfDate: JFXTextField
    lateinit var taContent: JFXTextArea
    lateinit var btnAcknowledge: JFXButton
    lateinit var btnBack: JFXButton
    lateinit var notification: Notification

    var exitCallback: (() -> Unit)? = null

    fun fillContent(notification: Notification) {
        this.tfId.text = notification.id
        this.tfSender.text = notification.senderId
        this.tfDate.text = DateHelper.fromDate(notification.date)
        this.taContent.text = notification.content
        this.notification = notification
    }



    override fun load(): UiElement {
        return UiLoader<NotificationDetailUiController>("/fxml/notificationui/NotificationDetailUi.fxml").loadAndGetElement()
    }

    override fun onClose() {
        exitCallback?.invoke()
    }


    fun onBtnAcknowledgeClicked(actionEvent: ActionEvent) {
        notification.read()

    }

    fun onBtnBackClicked(actionEvent: ActionEvent) {

    }
}