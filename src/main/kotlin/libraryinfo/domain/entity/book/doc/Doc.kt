package libraryinfo.domain.entity.book.doc

import libraryinfo.domain.entity.book.reader.Reader
import java.io.Serializable

interface Doc : Serializable {
    val reader: Reader
    val name: String
}