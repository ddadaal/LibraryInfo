package libraryinfo.domain.entity.user.usertype

import libraryinfo.domain.entity.user.strategy.bookmanagement.AdminBookManagementStrategy
import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.entity.user.strategy.borrow.SimpleBorrowStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.DynamicUserManagementStrategy
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.UserUiController
import java.time.Duration

class SystemType: UserType {
    override val name: String
        get() = "系统"

    override val borrowStrategy: BorrowStrategy
        get() = SimpleBorrowStrategy(Duration.ofHours(24*365), ArrayList())
    override val userManagementStrategy: UserManagementStrategy
        get() = DynamicUserManagementStrategy(
            canCreate = true,
            canSearch = true,
            canGenerateReport = true,
            canGeneratePayment = true
        )
    override val bookManagementStrategy: BookManagementStrategy
        get() = AdminBookManagementStrategy()
    override val mainUiElement: UiElement
        get() = UserUiController().load()
    override val isAdmin: Boolean
        get() = false

}
