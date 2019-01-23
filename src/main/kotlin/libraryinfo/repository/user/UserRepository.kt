package libraryinfo.repository.user

import libraryinfo.domain.user.User
import libraryinfo.domain.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.domain.user.strategy.borrow.BorrowStrategy
import libraryinfo.domain.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.domain.user.usertype.UserType
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.mainui.ManagerHomeUiController
import libraryinfo.presentation.mainui.ManagerUiController
import libraryinfo.repository.Repository
import java.time.LocalDateTime

object UserRepository: Repository() {

    fun getUser(username: String, password: String): User? {
        return User("1", username, LocalDateTime.now(), password, object : UserType {
            override val name: String
                get() = "学生"
            override val borrowStrategy: BorrowStrategy
                get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            override val userManagementStrategy: UserManagementStrategy
                get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            override val bookManagementStrategy: BookManagementStrategy
                get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
            override val mainUiElement: UiElement
                get() = ManagerUiController().load()
        })
    }
}