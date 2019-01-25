package libraryinfo.presentation.userui

import javafx.event.ActionEvent
import libraryinfo.presentation.helpui.CRUDSampleController
import libraryinfo.presentation.helpui.ModificationSampleController
import libraryinfo.presentation.mainui.Framework
import libraryinfo.presentation.mainui.MainUi
import libraryinfo.presentation.userui.reader.TxtReaderUiController

class UserUiController: MainUi("/fxml/userui/UserUi.fxml") {

    override lateinit var framework: Framework

    fun switchBackToHome() {
        framework.switchFunction(UserHomeUiController().load(), "主页")
    }

    fun onBrowseClicked(actionEvent: ActionEvent) {
        framework.switchFunction(BookBrowseUiController().load(), "浏览图书")
    }

    fun onMyBookClicked(actionEvent: ActionEvent) {
        framework.switchFunction(MyBookUiController().load(), "我的图书")

    }

    fun onProfileManagementClicked(actionEvent: ActionEvent) {
        framework.switchFunction(ProfileManagementUiController().load(), "管理个人信息")
    }

}