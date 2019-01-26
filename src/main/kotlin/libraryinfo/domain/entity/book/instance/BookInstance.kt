package libraryinfo.domain.entity.book.instance

import libraryinfo.domain.entity.book.Book
import libraryinfo.repository.book.BookRepository
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import java.io.Serializable
import java.util.*

class BookInstance() : Serializable {
    lateinit var id: UUID
    lateinit var bookId: UUID
    var recordId: UUID? = null

    val book: Book
        get() = BookRepository.data.find { it.id == bookId }!!


    constructor(id: UUID, bookId: UUID, recordId: UUID? = null) : this() {
        this.id = id
        this.bookId = bookId
        this.recordId = recordId
    }

    fun returnBook() {
        recordId = null
        book.`return`(this)
    }

    fun borrowBook(recordId: UUID) {
        this.recordId = recordId
        book.borrow(this)
    }

}
