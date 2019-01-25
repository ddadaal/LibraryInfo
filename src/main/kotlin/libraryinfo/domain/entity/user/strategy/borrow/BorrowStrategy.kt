package libraryinfo.domain.entity.user.strategy.borrow

import libraryinfo.domain.entity.book.Book
import java.io.Serializable
import java.time.Duration

interface BorrowStrategy : Serializable {
    fun canBorrowBook(book: Book, duration: Duration): Boolean
}
