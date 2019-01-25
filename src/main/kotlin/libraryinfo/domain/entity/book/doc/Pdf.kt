package libraryinfo.domain.entity.book.doc

import libraryinfo.domain.entity.book.reader.PDFReader
import libraryinfo.domain.entity.book.reader.Reader

class Pdf(var path: String) : Doc {
    override val reader: Reader = PDFReader(this)
    override val name: String = "PDF"

}
