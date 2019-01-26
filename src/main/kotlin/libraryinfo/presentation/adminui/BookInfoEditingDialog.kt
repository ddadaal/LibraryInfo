package libraryinfo.presentation.adminui

import libraryinfo.appservice.bookmanagement.BookManagementAppService
import libraryinfo.domain.entity.book.Book
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper

class BookInfoEditingDialog(val book: Book, val onClose: () -> Unit, title: String, content: String) : PromptDialogHelper(title, content) {

    init {
        val element = BookInfoUiController().load()
        setContent(element.component)
        val controller = element.getController<BookInfoUiController>()
        controller.setBook(book)
        addButton("确认修改", "") {
            val bookInfo = controller.bookInfo
            if (bookInfo != null) {
                BookManagementAppService.SERVICE.editBook(book.id, bookInfo)
                Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
                onClose()
            }
        }
    }


}