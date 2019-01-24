package libraryinfo.presentation.mainui

import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.internal.UiLoader

abstract class MainUi(var fxmlUrl: String) : UiController, ConcreteFrameworkUi {
    override fun load(): UiElement {
        return UiLoader(fxmlUrl).loadAndGetElement()
    }
}