package libraryinfo.domain.user.strategy.borrow

import java.time.Duration

interface BorrowStrategy {
    fun borrowBook(bookId: String, duration: Duration): Boolean
}
