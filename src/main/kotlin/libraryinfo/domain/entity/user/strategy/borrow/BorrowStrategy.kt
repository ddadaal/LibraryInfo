package libraryinfo.domain.entity.user.strategy.borrow

import libraryinfo.domain.entity.book.Book
import java.io.Serializable
import java.time.Duration

interface BorrowStrategy : Serializable {
    val maxDuration: Duration
    fun canBorrowBook(book: Book): Boolean
}
