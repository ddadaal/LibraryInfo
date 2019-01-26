package libraryinfo.domain.entity.book.instance

import libraryinfo.domain.entity.book.Book
import libraryinfo.repository.book.BookRepository
import libraryinfo.util.Id
import java.io.Serializable

class BookInstance() : Serializable {
    lateinit var id: Id
    lateinit var bookId: Id
    var recordId: Id? = null

    val book: Book
        get() = BookRepository.data.find { it.id == bookId }!!


    constructor(id: Id, bookId: Id, recordId: Id? = null) : this() {
        this.id = id
        this.bookId = bookId
        this.recordId = recordId
    }

    fun returnBook() {
        recordId = null
        book.`return`(this)
    }

    fun borrowBook(recordId: Id) {
        this.recordId = recordId
        book.borrow(this)
    }

}
