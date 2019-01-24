package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.DynamicUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement

object AdminType: UserType {
    override val name: String
        get() = "管理员"
    override val borrowStrategy: BorrowStrategy
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val userManagementStrategy: UserManagementStrategy
        get() = DynamicUserManagementStrategy(true, true)
    override val bookManagementStrategy: BookManagementStrategy
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val mainUiElement: UiElement
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val isAdmin: Boolean
        get() = true

}