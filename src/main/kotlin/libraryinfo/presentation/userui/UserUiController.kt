package libraryinfo.presentation.userui

import javafx.event.ActionEvent
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.internal.UiLoader
import libraryinfo.presentation.mainui.FrameworkUiController
import libraryinfo.presentation.mainui.ManagerUiController
import libraryinfo.presentation.userui.reader.TxtReaderUiController

class UserUiController: FrameworkUiController() {
    override fun load(): UiElement {
        return UiLoader<UserUiController>("/fxml/userui/UserUi.fxml").loadAndGetElement()
    }

    override fun switchBackToHome() {
        switchFunction(ManagerUiController::class.java, "主界面", true)
    }

    fun onLibraryClicked(actionEvent: ActionEvent) {
        switchFunction(TxtReaderUiController::class.java, "阅读器", true)
    }

    fun onBorrowClicked(actionEvent: ActionEvent) {

    }

    fun onProfileClicked(actionEvent: ActionEvent) {

    }

}