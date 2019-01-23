package libraryinfo.entity.user.usertype

import libraryinfo.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.presentation.internal.UiElement
import java.io.Serializable

interface UserType: Serializable {
    val name: String

    val borrowStrategy: BorrowStrategy

    val userManagementStrategy: UserManagementStrategy

    val bookManagementStrategy: BookManagementStrategy

    val mainUiElement: UiElement

}
