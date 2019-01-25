package libraryinfo.presentation.userui

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class ProfileManagementUiController: UiController {
    override fun load(): UiElement {
        return doLoad("/fxml/userui/ProfileManagement.fxml")
    }

}