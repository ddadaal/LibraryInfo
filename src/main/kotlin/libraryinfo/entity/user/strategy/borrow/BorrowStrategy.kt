package libraryinfo.entity.user.strategy.borrow

import libraryinfo.entity.book.Book
import java.io.Serializable
import java.time.Duration

interface BorrowStrategy : Serializable {
    fun canBorrowBook(book: Book, duration: Duration): Boolean
}
