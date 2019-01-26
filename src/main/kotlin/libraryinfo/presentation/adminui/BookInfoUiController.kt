package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXTextField
import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.vo.bookmanagement.BookEditInfoVo

class BookInfoUiController : UiController {

    lateinit var nameInput: JFXTextField
    lateinit var categoryInput: JFXTextField
    lateinit var docs: ArrayList<Doc>

    val bookInfo: BookEditInfoVo?
        get() =
            if (nameInput.text != "" && categoryInput.text != "") BookEditInfoVo(nameInput.text, categoryInput.text, docs)
            else null

    override fun load(): UiElement {
        return doLoad("/fxml/adminui/BookInfo.fxml")
    }

    fun setBook(book: Book) {
        nameInput.text = book.name
        categoryInput.text = book.category
        docs = book.availableDocs
    }


}