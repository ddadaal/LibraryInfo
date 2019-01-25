package libraryinfo.domain.entity.book.instance

import libraryinfo.domain.entity.book.Book
import libraryinfo.repository.book.BookRepository
import java.io.Serializable
import java.util.*

class BookInstance(): Serializable {
    lateinit var id: UUID
    lateinit var bookId: UUID

    val book: Book
        get() = BookRepository.data.find { it.id == bookId }!!


    constructor(id: UUID, bookId: UUID): this() {
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
