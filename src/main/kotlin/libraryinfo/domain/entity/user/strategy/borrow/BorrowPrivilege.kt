package libraryinfo.domain.entity.user.strategy.borrow

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.user.User
import java.io.Serializable
import java.time.Duration

interface BorrowPrivilege : Serializable {
    val maxDuration: Duration
    fun judgeBookBorrowability(user: User, book: Book): BookBorrowability
}
