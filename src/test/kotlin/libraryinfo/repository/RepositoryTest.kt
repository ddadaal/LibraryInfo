package libraryinfo.repository

import libraryinfo.entity.book.Book
import libraryinfo.entity.notification.Notification
import libraryinfo.entity.user.User
import libraryinfo.entity.user.strategy.bookmanagement.BookManagementStrategy
import libraryinfo.entity.user.strategy.borrow.BorrowStrategy
import libraryinfo.entity.user.strategy.usermanagement.UserManagementStrategy
import libraryinfo.entity.user.usertype.UndergraduateType
import libraryinfo.entity.user.usertype.UserType
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.mainui.ManagerUiController
import libraryinfo.repository.user.UserRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.Duration
import java.time.LocalDateTime

class RepositoryTest {

    @Test
    fun insertInitialData() {

        UserRepository.save(
            arrayListOf(
                User(
                    "123",
                    "test",
                    "Test User",
                    "test",
                    UndergraduateType,
                    arrayListOf(
                        Notification(
                            LocalDateTime.now(),
                            "123",
                            "Test notification"
                        )
                    ),
                    ArrayList()
                )
            )
        )
    }
}