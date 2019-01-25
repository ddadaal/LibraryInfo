package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXTextField
import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.vo.bookmanagement.BookInfoVo
import libraryinfo.vo.usermanagement.UserInfoVo

class BookInfoUiController : UiController {

    lateinit var nameInput: JFXTextField
    lateinit var categoryInput: JFXTextField

    val bookInfo: BookInfoVo?
        get() =
            if (nameInput.text != "" && categoryInput.text != "") BookInfoVo(nameInput.text, categoryInput.text, ArrayList())
            else null

    override fun load(): UiElement {
        return doLoad("/fxml/adminui/BookInfo.fxml")
    }

    fun setBook(book: Book) {
        nameInput.text = book.name
        categoryInput.text = book.category
    }


}