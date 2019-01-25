package libraryinfo.presentation.adminui

import javafx.scene.text.Text
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.util.DateHelper

class AdminHomeUiController : UiController {
    lateinit var textWelcome: Text
    lateinit var textLoginTime: Text

    val loginAppService = LoginAppServiceFactory.service

    override fun load(): UiElement {
        return doLoad("/fxml/adminui/AdminHomeUi.fxml")
    }

    fun initialize() {
        textWelcome.text += loginAppService.currentUser?.name
        textLoginTime.text += DateHelper.fromDate(Globals.loginTime)
    }
    fun onUserManagementClicked(){
        Globals.framework.switchFunction(UserManagementUiController().load(),"管理用户")
    }

    fun onBookManagementClicked(){
        Globals.framework.switchFunction(BookManagementUiController().load(),"管理图书")
    }

    fun onNotificationClicked(){

    }
}