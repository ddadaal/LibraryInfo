package libraryinfo.presentation.internal

/**
 * External loadable ui. If a ui can be externally loaded, implement this interface.
 */
interface UiController {
    /**
     * Loads the controller.
     * @return external loaded ui controller and component
     */
    fun load(): UiElement

    fun onClose() {

    }


    fun doLoad(fxmlPath: String): UiElement {
        return UiLoader(fxmlPath).loadAndGetElement()
    }
}
