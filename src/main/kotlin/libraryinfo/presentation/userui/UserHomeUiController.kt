package libraryinfo.presentation.userui

import javafx.event.ActionEvent
import javafx.scene.text.Text
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.util.DateHelper

class UserHomeUiController : UiController {

    lateinit var textWelcome: Text
    lateinit var textLoginTime: Text

    val loginAppService = LoginAppServiceFactory.service

    override fun load(): UiElement {
        return doLoad("/fxml/userui/UserHomeUi.fxml")
    }

    fun initialize() {
        textWelcome.text += loginAppService.currentUser?.name
        textLoginTime.text += DateHelper.fromDate(Globals.loginTime)
    }

    fun onBtnNotificationClicked(actionEvent: ActionEvent) {
        Globals.framework.onNotificationFunctionButtonClicked(actionEvent)
    }

    fun onBrowseClicked(actionEvent: ActionEvent) {
        Globals.framework.switchFunction(BookBrowseUiController().load(), "浏览图书")

    }

    fun onMyBookClicked(actionEvent: ActionEvent) {
        Globals.framework.switchFunction(MyBookUiController().load(), "我的图书")
    }

    fun onProfileManagementClicked(actionEvent: ActionEvent) {
        Globals.framework.switchFunction(ProfileManagementUiController().load(), "管理个人信息")

    }


}
