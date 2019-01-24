package libraryinfo.domain.entity.book.instance

import libraryinfo.domain.entity.book.Book
import libraryinfo.repository.book.BookRepository
import java.io.Serializable

class BookInstance(): Serializable {
    lateinit var id: String
    lateinit var bookId: String

    val book: Book
        get() = BookRepository.data.find { it.id == bookId }!!


    constructor(id: String, bookId: String, recordId: String?): this() {
        this.id = id
        this.bookId = bookId
    }

    fun returnBook() {

        book.`return`(this)

        BookRepository.save()

    }

    fun borrowBook() {
        book.borrow(this)
        BookRepository.save()
    }

}
