package libraryinfo.presentation.userui

import com.jfoenix.controls.*
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.scene.control.SelectionMode
import javafx.scene.input.KeyCode
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.book.BookRepository
import libraryinfo.util.DateHelper


class BookModel(val book: Book) : RecursiveTreeObject<BookModel>() {
    val idProperty = SimpleStringProperty(book.id.toString())
    val nameProperty = SimpleStringProperty(book.name)
    val categoryProperty = SimpleStringProperty(book.category)
    val instanceRemainingProperty = SimpleObjectProperty(book.instances.filter { it.recordId == null }.count())
    val formatsProperty = SimpleObjectProperty(book.availableDocs)
}

class BookTableUiController : UiController {

    lateinit var tfSearch: JFXTextField
    lateinit var tableBooks: JFXTreeTableView<BookModel>
    lateinit var tcId: JFXTreeTableColumn<BookModel, String>
    lateinit var tcName: JFXTreeTableColumn<BookModel, String>
    lateinit var tcCategory: JFXTreeTableColumn<BookModel, String>
    lateinit var tcInstanceRemaining: JFXTreeTableColumn<BookModel, String>
    lateinit var tcFormat: JFXTreeTableColumn<BookModel, String>

    private val books: ObservableList<BookModel> = FXCollections.observableArrayList()

    private val loginAppService = LoginAppServiceFactory.service


    val selected: Book?
        get() {
            return try {
                tableBooks.selectionModel.selectedItem.value.book

            } catch (e: Exception) {
                PromptDialogHelper("未选择！", "请先选择一本书！")
                        .addCloseButton("好", "CHECK", null)
                        .createAndShow()
                null
            }

        }

    fun initialize() {
        initTable()
        updateItems()
    }

    fun updateItems() {
        val query = tfSearch.text
        val searchResult = BookRepository.data.filter { it.name.contains(query) || it.category.contains(query) || it.id.toString() == query }
        books.clear()
        books.addAll(searchResult.map { BookModel(it) })
    }

    private fun initTable() {

        tfSearch.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
//                cbPosition.setValue(null)
                updateItems()
            }
        }

        tcId.setCellValueFactory { SimpleStringProperty(it.value.value.idProperty.get().substring(0, 6)) }
        tcName.setCellValueFactory { it.value.value.nameProperty }
        tcCategory.setCellValueFactory { it.value.value.categoryProperty }
        tcInstanceRemaining.setCellValueFactory { SimpleStringProperty(it.value.value.instanceRemainingProperty.get().toString()) }
        tcFormat.setCellValueFactory { SimpleStringProperty(it.value.value.formatsProperty.get().joinToString(", ") { doc -> doc.name }) }

        val root = RecursiveTreeItem<BookModel>(books) { it.children }
        tableBooks.root = root
        tableBooks.isShowRoot = false
        tableBooks.selectionModel.selectionMode = SelectionMode.SINGLE
    }

    override fun load(): UiElement {
        return doLoad("/fxml/userui/BookTable.fxml")
    }

}