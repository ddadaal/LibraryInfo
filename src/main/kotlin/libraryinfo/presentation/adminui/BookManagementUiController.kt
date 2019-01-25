package libraryinfo.presentation.adminui

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.SimpleStringProperty
import libraryinfo.domain.entity.book.Book
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement


class BookModel(val book: Book) : RecursiveTreeObject<UserModel>() {
    val idProperty = SimpleStringProperty(book.id.toString())
    val nameProperty = SimpleStringProperty(book.name)
    val categoryProperty = SimpleStringProperty(book.category)
    val typeProperty = SimpleStringProperty(user.type.name)
}

class BookManagementUiController:UiController {
    override fun load(): UiElement {
       return doLoad("/fxml/adminui/BookManagement.fxml")
    }
}