package libraryinfo.presentation.userui.reader.pdf

import libraryinfo.domain.entity.book.doc.Pdf
import libraryinfo.presentation.userui.reader.Reader
import libraryinfo.presentation.internal.UiElement

class PdfReader(override val doc: Pdf) : Reader {

    override fun display(): UiElement {
        val ui = PDFReaderUiController().load()
        val controller = ui.getController<PDFReaderUiController>()
        controller.fillContent(doc)
        return ui
    }
}