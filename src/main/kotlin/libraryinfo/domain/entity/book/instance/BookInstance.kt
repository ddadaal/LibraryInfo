package libraryinfo.domain.entity.book.instance

import libraryinfo.domain.entity.book.Book
import libraryinfo.repository.book.BookRepository
import java.io.Serializable

class BookInstance(): Serializable {
    lateinit var id: String
    lateinit var bookId: String

    val book: Book
        get() = BookRepository.data.find { it.id == bookId }!!


    constructor(id: String, bookId: String): this() {
        this.id = id
        this.bookId = bookId
    }

    fun returnBook() {
        book.`return`(this)
    }

    fun borrowBook() {
        book.borrow(this)
    }

}
