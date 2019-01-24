package libraryinfo.domain.entity.user.strategy.borrow

import libraryinfo.domain.entity.book.Book
import java.io.Serializable
import java.time.Duration

interface BorrowStrategy : Serializable {
    val duration: Duration
    val availableCategories: ArrayList<String>
    fun canBorrowBook(book: Book, duration: Duration): Boolean {
        return book.category in availableCategories && duration < this.duration
    }
}
