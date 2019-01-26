package libraryinfo.presentation.userui.reader

import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.domain.entity.book.doc.Pdf
import libraryinfo.domain.entity.book.doc.Txt
import libraryinfo.presentation.userui.reader.pdf.PdfReader
import libraryinfo.presentation.userui.reader.txt.TxtReader
import java.lang.IllegalArgumentException

object ReaderFactory {
    fun getReader(doc: Doc): Reader {
        return when (doc) {
            is Pdf -> PdfReader(doc)
            is Txt -> TxtReader(doc)
            else -> throw IllegalArgumentException()
        }
    }
}
