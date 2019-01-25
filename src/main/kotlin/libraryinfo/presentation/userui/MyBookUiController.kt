package libraryinfo.presentation.userui

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class MyBookUiController: UiController {
    override fun load(): UiElement {
        return doLoad("/fxml/userui/MyBook.fxml")
    }

}