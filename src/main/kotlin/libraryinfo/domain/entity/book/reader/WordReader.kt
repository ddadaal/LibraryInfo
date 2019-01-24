package libraryinfo.domain.entity.book.reader

import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.reader.WordReaderUiController

class WordReader(override val doc: Doc): Reader {
    override fun display(): UiElement {
        return WordReaderUiController().load()
    }
}