package libraryinfo.entity.doc

import libraryinfo.presentation.internal.UiElement
import java.io.Serializable

abstract class Doc: Serializable {
    abstract fun getReader(): UiElement
}