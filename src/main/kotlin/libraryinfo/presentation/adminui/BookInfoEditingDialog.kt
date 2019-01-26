package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.bookmanagement.BookInfoVo

class BookInfoEditingDialog(val book: Book, val onClose: () -> Unit, title: String, content: String) : PromptDialogHelper(title, content) {

    init {
        val element = BookInfoUiController().load()
        setContent(element.component)
        val controller = element.getController<BookInfoUiController>()
        controller.setBook(book)
        addButton("确认修改", "") {
            val bookInfo = controller.bookInfo
            if (bookInfo != null) {
                book.edit(bookInfo)
                Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
                onClose()
            }
        }
    }


}