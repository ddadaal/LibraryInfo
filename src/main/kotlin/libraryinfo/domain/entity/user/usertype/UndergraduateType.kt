package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.DynamicUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.UserUiController
import java.time.Duration

class UndergraduateType: UserType {

    override val name: String = "本科生"

    override val borrowStrategy = object : BorrowStrategy {
        override fun canBorrowBook(book: Book, duration: Duration): Boolean {
            return true
        }
    }

    override val userManagementStrategy = DynamicUserManagementStrategy(false, false)

    override val bookManagementStrategy: BookManagementStrategy = TODO()

    override val mainUiElement: UiElement
        get() = UserUiController().load()

    override val isAdmin: Boolean = false

}
