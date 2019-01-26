package libraryinfo.presentation.userui.reader.txt

import com.jfoenix.controls.JFXTextArea
import libraryinfo.domain.entity.book.doc.Txt
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class TxtReaderUiController: UiController {

    lateinit var taContent: JFXTextArea

    override fun load(): UiElement {
        return doLoad("/fxml/userui/reader/TxtReader.fxml")
    }

    fun fillContent(txt: Txt) {
        taContent.text = txt.content
    }

}