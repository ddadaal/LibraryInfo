package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.bookmanagement.NoBookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.borrow.GraduateBorrowStrategy
import libraryinfo.domain.entity.user.strategy.borrow.UndergraduateBorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.DynamicUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement

class GraduateType : UserType {
    override val name: String = "研究生"

    override val borrowStrategy: BorrowStrategy =GraduateBorrowStrategy()

    override val userManagementStrategy = DynamicUserManagementStrategy()

    override val bookManagementStrategy: BookManagementStrategy = NoBookManagementStrategy()

    override val mainUiElement: UiElement
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val isAdmin: Boolean = false

}
