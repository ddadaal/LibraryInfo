package libraryinfo.domain.entity.book.doc

import libraryinfo.domain.entity.book.reader.Reader
import libraryinfo.domain.entity.book.reader.WordReader

class Word : Doc {
    override val reader: Reader = WordReader(this)
    override val name: String
        get() = "Word"
}
