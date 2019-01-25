package libraryinfo.presentation.adminui

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class BookManagementUiController:UiController {
    override fun load(): UiElement {
       return doLoad("/fxml/adminui/BookManagement.fxml")
    }
}