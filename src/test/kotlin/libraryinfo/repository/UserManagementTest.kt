package libraryinfo.repository

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.usertype.AdminType
import libraryinfo.domain.entity.user.usertype.UndergraduateType
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class UserManagementTest {
    private val userId = UUID.randomUUID()

    @Test
    fun testPenalty() {
        val user = UserRepository.data.find { it.id ==userId }!!
        user.borrowRecords.clear()
        user.borrowRecords.addAll(
                arrayListOf(
                        BorrowRecordVo(LocalDateTime.now().minusDays(16),
                                UUID.randomUUID(),
                                Duration.ofDays(15),
                                null),
                        BorrowRecordVo(LocalDateTime.now().minusDays(13),
                                UUID.randomUUID(),
                                Duration.ofDays(15),
                                null),
                        BorrowRecordVo(LocalDateTime.now().minusDays(16),
                                UUID.randomUUID(),
                                Duration.ofDays(15),
                                LocalDateTime.MIN)
                )
        )
        assert(UserRepository.data.find { it.isAdmin }!!.type.userManagementStrategy.generatePenaltyPayment(userId).fee == 1.0)
    }

    @BeforeEach
    fun insertInitialData() {

        UserRepository.save(
                arrayListOf(
                        User(
                                userId,
                                "test",
                                "Test User",
                                "test",
                                UndergraduateType(),
                                arrayListOf(
                                        Notification(
                                                LocalDateTime.now(),
                                                userId,
                                                "Test notification"
                                        )
                                ),
                                ArrayList(),
                                ArrayList()
                        ),
                        User(UUID.randomUUID(),
                                "admin",
                                "admin",
                                "admin",
                                AdminType(),
                                ArrayList(),
                                ArrayList(),
                                ArrayList()
                        )
                )
        )

        val book1Id = UUID.randomUUID()
        val book2Id = UUID.randomUUID()

        BookRepository.save(
                arrayListOf(
                        Book(
                                book1Id,
                                "1",
                                "1",
                                ArrayList(),
                                arrayListOf(
                                        BookInstance(UUID.randomUUID(), book1Id),
                                        BookInstance(UUID.randomUUID(), book1Id)
                                )
                        ),
                        Book(
                                book2Id,
                                "2",
                                "2",
                                ArrayList(),
                                arrayListOf(
                                        BookInstance(UUID.randomUUID(), book2Id),
                                        BookInstance(UUID.randomUUID(), book2Id)
                                )
                        )
                )
        )


    }

}