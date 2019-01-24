package libraryinfo.presentation.userui.reader

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.internal.UiLoader

class TxtReaderUiController: UiController {
    override fun load(): UiElement {
        return UiLoader<TxtReaderUiController>("/fxml/userui/reader/TxtReader.fxml").loadAndGetElement()
    }

}