package libraryinfo.domain.entity.book.reader

import libraryinfo.domain.entity.book.doc.Txt
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.reader.TxtReaderUiController

class TxtReader(override val doc: Txt): Reader {
    override fun display(): UiElement {
        val ui = TxtReaderUiController().load()
        val controller = ui.getController<TxtReaderUiController>()
        controller.fillContent(doc)
        return ui
    }
}