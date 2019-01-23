package libraryinfo.presentation.internal

import javafx.scene.Parent


@Suppress("UNCHECKED_CAST")
class UiElement(val component: Parent, private val controller: UiController) {

    fun <T : UiController> getController(): T {
        return controller as T
    }
}
