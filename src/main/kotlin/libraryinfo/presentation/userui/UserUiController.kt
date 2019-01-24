package libraryinfo.presentation.userui

import javafx.event.ActionEvent
import libraryinfo.presentation.mainui.Framework
import libraryinfo.presentation.mainui.MainUi
import libraryinfo.presentation.userui.reader.TxtReaderUiController

class UserUiController: MainUi("/fxml/userui/UserUi.fxml") {

    override lateinit var framework: Framework

    fun switchBackToHome() {
        println("switched back to home")
    }

    fun onLibraryClicked(actionEvent: ActionEvent) {
        framework.switchFunction(TxtReaderUiController().load(), "阅读器")
    }

    fun onBorrowClicked(actionEvent: ActionEvent) {

    }

    fun onProfileClicked(actionEvent: ActionEvent) {

    }

}