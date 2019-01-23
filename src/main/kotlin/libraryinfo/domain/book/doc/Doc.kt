package libraryinfo.domain.book.doc

import libraryinfo.domain.book.reader.Reader

interface Doc {
    val reader: Reader
    val name: String
}