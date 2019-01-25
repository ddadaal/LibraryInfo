package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.bookmanagement.NoBookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.borrow.UndergraduateBorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.DynamicUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.UserUiController
import libraryinfo.vo.bookmanagement.BookInfoVo
import java.time.Duration

class UndergraduateType : UserType {

    override val name: String = "本科生"

    override val borrowStrategy = UndergraduateBorrowStrategy()

    override val userManagementStrategy = DynamicUserManagementStrategy()

    override val bookManagementStrategy: BookManagementStrategy = NoBookManagementStrategy()

    override val mainUiElement: UiElement
        get() = UserUiController().load()

    override val isAdmin: Boolean = false

}
