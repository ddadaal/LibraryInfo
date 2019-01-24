package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.bookmanagement.NoBookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.borrow.TeacherBorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.DynamicUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement

class TeacherType : UserType {

    override val name: String = "老师"

    override val borrowStrategy: BorrowStrategy
        get() = TeacherBorrowStrategy()
    override val userManagementStrategy = DynamicUserManagementStrategy(false, false)

    override val bookManagementStrategy: BookManagementStrategy = NoBookManagementStrategy()

    override val mainUiElement: UiElement
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val isAdmin: Boolean = false

}