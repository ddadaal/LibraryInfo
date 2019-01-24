package libraryinfo.domain.entity.book.reader

import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.reader.PDFReaderUiController

class PDFReader(override val doc: Doc) : Reader {

    override fun display(): UiElement {
        return PDFReaderUiController().load()
    }
}