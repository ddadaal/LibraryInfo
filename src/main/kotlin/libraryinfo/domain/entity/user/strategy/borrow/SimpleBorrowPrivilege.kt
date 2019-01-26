package libraryinfo.domain.entity.user.strategy.borrow

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.user.User
import java.time.Duration

open class SimpleBorrowPrivilege(
    override var maxDuration: Duration,
    var maximumNumber: Int,
    var availableCategories: List<String>
): BorrowPrivilege {

    override fun judgeBookBorrowability(user: User, book: Book): BookBorrowability {
        if (user.ownedBookInstanceIds.size >= maximumNumber) {
            return BookBorrowability("超过最大借书数量$maximumNumber")
        }
        if (!availableCategories.contains(book.category)) {
            return BookBorrowability("您只可借${availableCategories.joinToString(", ")}类型的书，不可借${book.category}类型的书")
        }
        return BookBorrowability()
    }
}