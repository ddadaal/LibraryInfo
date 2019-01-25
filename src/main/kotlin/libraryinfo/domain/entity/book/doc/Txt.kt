package libraryinfo.domain.entity.book.doc

import libraryinfo.domain.entity.book.reader.Reader
import libraryinfo.domain.entity.book.reader.TxtReader

class Txt(var content: String) : Doc {

    override val reader: Reader = TxtReader(this)
    override val name: String = "TXT"
}
