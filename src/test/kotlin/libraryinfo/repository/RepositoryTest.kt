package libraryinfo.repository

import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.usertype.UndergraduateType
import libraryinfo.repository.user.UserRepository
import org.junit.jupiter.api.Test

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
                    UndergraduateType(),
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