package libraryinfo.entity.book.doc

import libraryinfo.entity.book.reader.Reader
import java.io.Serializable

interface Doc : Serializable {
    val reader: Reader
    val name: String
}