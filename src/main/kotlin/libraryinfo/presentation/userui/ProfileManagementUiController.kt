package libraryinfo.presentation.userui

import com.jfoenix.controls.JFXButton
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class ProfileManagementUiController: UiController {

    lateinit var modifyButton:JFXButton
    override fun load(): UiElement {
        return doLoad("/fxml/userui/ProfileManagement.fxml")
    }

    fun onModifyButtonClicked(){

    }

}