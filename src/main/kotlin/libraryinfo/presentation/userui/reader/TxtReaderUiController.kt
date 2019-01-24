package libraryinfo.presentation.userui.reader

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class TxtReaderUiController: UiController {
    override fun load(): UiElement {
        return doLoad("/fxml/userui/reader/TxtReader.fxml")
    }

}