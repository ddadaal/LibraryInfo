package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.NoUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.UserUiController
import java.time.Duration

object UndergraduateType: UserType {
    override val name: String
        get() = "本科生"
    override val borrowStrategy: BorrowStrategy
        get() = object : BorrowStrategy {
            override fun canBorrowBook(book: Book, duration: Duration): Boolean {
                return true
            }
        }
    override val userManagementStrategy: UserManagementStrategy
        get() = NoUserManagementStrategy
    override val bookManagementStrategy: BookManagementStrategy
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val mainUiElement: UiElement
        get() = UserUiController().load()
    override val isAdmin: Boolean
        get() = false

}
