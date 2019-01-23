package libraryinfo.presentation.internal

import javafx.fxml.FXMLLoader
import libraryinfo.Client

class UiLoader<T: UiController>(location: String) {
    private val loader = FXMLLoader()

    init {
        setLocation(location)

    }

    fun setLocation(path: String) {
        loader.location = Client::class.java.getResource(path)
    }


    val controller: T
        get() = loader.getController<T>()

    fun loadAndGetElement(): UiElement {
        return UiElement(loader.load(), controller)
    }
}
