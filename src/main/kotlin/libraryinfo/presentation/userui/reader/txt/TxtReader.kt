package libraryinfo.presentation.userui.reader.txt

import libraryinfo.domain.entity.book.doc.Txt
import libraryinfo.presentation.userui.reader.Reader
import libraryinfo.presentation.internal.UiElement

class TxtReader(override val doc: Txt): Reader {
    override fun display(): UiElement {
        val ui = TxtReaderUiController().load()
        val controller = ui.getController<TxtReaderUiController>()
        controller.fillContent(doc)
        return ui
    }
}