package libraryinfo.domain.entity.book.doc

import libraryinfo.domain.entity.book.reader.PDFReader
import libraryinfo.domain.entity.book.reader.Reader

class PDF : Doc {
    override val reader: Reader = PDFReader(this)
    override val name: String
        get() = "PDF"

}
