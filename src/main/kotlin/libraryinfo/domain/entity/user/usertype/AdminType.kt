package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.DynamicUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement

class AdminType: UserType {

    override val name = "管理员"

    override val borrowStrategy: BorrowStrategy = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val userManagementStrategy = DynamicUserManagementStrategy(true, true)

    override val bookManagementStrategy: BookManagementStrategy = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val mainUiElement: UiElement
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val isAdmin: Boolean = true

}