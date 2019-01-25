package libraryinfo.presentation.adminui

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.HBox
import libraryinfo.domain.entity.book.Book
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.BookTableUiController


class BookManagementUiController : UiController {

    lateinit var container: HBox
    private lateinit var tableController: BookTableUiController

    fun initialize() {
        val tableElement = BookTableUiController().load()
        container.children.add(tableElement.component)
        tableController = tableElement.getController()
    }

    override fun load(): UiElement {
        return doLoad("/fxml/adminui/BookManagement.fxml")
    }

    fun onModifyButtonClicked() {
        val book = tableController.selected
        if (book != null) {
            BookInfoEditingDialog(book, { tableController.updateItems() }, "修改图书信息", "")
                    .createAndShow()
        }
    }
}