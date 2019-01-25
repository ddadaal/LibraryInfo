package libraryinfo.domain.entity.book.reader

import libraryinfo.domain.entity.book.doc.Pdf
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.reader.PDFReaderUiController

class PDFReader(override val doc: Pdf) : Reader {

    override fun display(): UiElement {
        val ui = PDFReaderUiController().load()
        val controller = ui.getController<PDFReaderUiController>()
        controller.fillContent(doc)
        return ui
    }
}