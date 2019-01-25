package libraryinfo.domain.entity.user.strategy.borrow

import libraryinfo.domain.entity.book.Book
import java.time.Duration

open class SimpleBorrowStrategy(
    override var maxDuration: Duration,
    var availableCategories: List<String>
): BorrowStrategy {
    override fun canBorrowBook(book: Book): Boolean {
        return book.category in availableCategories
    }
}