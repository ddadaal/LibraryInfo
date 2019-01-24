package libraryinfo.presentation.userui.reader

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class PDFReaderUiController: UiController {
    override fun load(): UiElement {
        return doLoad("")
    }
}