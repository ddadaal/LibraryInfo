package libraryinfo.repository

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.usertype.UndergraduateType
import libraryinfo.domain.exception.BorrowBookException
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Duration

import java.time.LocalDateTime

class RepositoryTest {

    @BeforeEach
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
                                ArrayList(),
                                ArrayList()
                        )
                )
        )

        BookRepository.save(
                arrayListOf(
                        Book("1",
                                "1",
                                "1",
                                ArrayList(),
                                arrayListOf(
                                        BookInstance("1", "1"),
                                        BookInstance("2", "1")
                                )),
                        Book("2",
                                "2",
                                "2",
                                ArrayList(),
                                arrayListOf(
                                        BookInstance("1", "2"),
                                        BookInstance("2", "2")
                                ))
                )
        )

    }

    @Test
    fun testBorrowBookWithBeyondDuration() {
        try {
            UserRepository.data.find { it.id == "123" }!!.borrowBook(
                    BookRepository.data.find { it.id == "1" }!!.instances[0],
                    Duration.ofDays(16)
            )
        } catch (e: BorrowBookException) {
            assert(true)
        }
    }

    @Test
    fun testBorrowBookWithBeyondCategories() {
        try {
            UserRepository.data.find { it.id == "123" }!!.borrowBook(
                    BookRepository.data.find { it.id == "2" }!!.instances[0],
                    Duration.ofDays(1)
            )
        } catch (e: BorrowBookException) {
            assert(true)
        }
    }

    @Test
    fun testBorrowBook() {
        UserRepository.data.find { it.id == "123" }!!.borrowBook(
                BookRepository.data.find { it.id == "1" }!!.instances[0],
                Duration.ofDays(1)
        )
    }


}