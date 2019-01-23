package libraryinfo.domain.doc

import libraryinfo.presentation.internal.UiElement

abstract class Doc {
    abstract fun getReader(): UiElement
}