package libraryinfo.domain.entity.book.reader

import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.presentation.internal.UiElement
import java.io.Serializable

interface Reader : Serializable {
    val doc: Doc
    fun display():UiElement
}