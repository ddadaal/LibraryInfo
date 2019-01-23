package libraryinfo.presentation.helpui

import libraryinfo.presentation.internal.UiElement

interface ContentDisplayUi<T> {

    fun showContent(arg: T): UiElement
}
