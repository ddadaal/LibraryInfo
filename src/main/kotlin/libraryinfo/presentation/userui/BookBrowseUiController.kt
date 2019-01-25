package libraryinfo.presentation.userui

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class BookBrowseUiController: UiController {
    override fun load(): UiElement {
        return doLoad("/fxml/userui/BookBrowse.fxml")
    }

}