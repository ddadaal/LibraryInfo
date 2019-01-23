package libraryinfo.presentation.mainui

import com.jfoenix.controls.JFXButton
import javafx.scene.text.Text
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.UiController
import libraryinfo.util.DateHelper

abstract class BaseHomepageUiController : UiController {
    val frameworkUiController
        get() = Globals.frameworkUiController
    lateinit var btnNotification: JFXButton
    lateinit var textLoginTime: Text
    lateinit var textWelcome: Text

    fun refresh() {
        val notificationCount = frameworkUiController.refreshNotificationStatus()

        btnNotification.text = notificationCount.toString()
        textLoginTime.text = "登录时间：" + DateHelper.fromDate(Globals.loginTime)

    }
}
