package libraryinfo.presentation.internal

import javafx.fxml.FXMLLoader
import libraryinfo.Client

class UiLoader(location: String) {
    private val loader = FXMLLoader()

    init {
        setLocation(location)

    }

    fun setLocation(path: String) {
        loader.location = Client::class.java.getResource(path)
    }


    fun <T: UiController> getController(): T {
        return loader.getController()
    }

    fun loadAndGetElement(): UiElement {
        return UiElement(loader.load(), getController())
    }
}
