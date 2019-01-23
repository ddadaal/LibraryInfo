package libraryinfo.domain.user.usertype

import libraryinfo.domain.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement

interface UserType {
    val name: String

    val borrowStrategy: BorrowStrategy

    val userManagementStrategy: UserManagementStrategy

    val bookManagementStrategy: BookManagementStrategy

    val mainUiElement: UiElement

}
