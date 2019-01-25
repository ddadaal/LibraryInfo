package libraryinfo.repository

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.doc.Pdf
import libraryinfo.domain.entity.book.doc.Txt
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.usertype.UndergraduateType
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import org.junit.jupiter.api.Test

import java.time.LocalDateTime
import java.util.*

class RepositoryTest {

    @Test
    fun insertInitialData() {
        val userId = UUID.randomUUID()

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
                    arrayListOf(
                        Txt("这是书1的txt文本内容。"),
                        Pdf("https://www.gnu.org/software/gnu-c-manual/gnu-c-manual.pdf")
                    ),
                    arrayListOf(
                        BookInstance(UUID.randomUUID(), book1Id),
                        BookInstance(UUID.randomUUID(), book1Id)
                    )
                ),
                Book(
                    book2Id,
                    "2",
                    "2",
                    arrayListOf(
                        Txt("这是书2的txt文本内容。")
                    ),
                    arrayListOf(
                        BookInstance(UUID.randomUUID(), book2Id),
                        BookInstance(UUID.randomUUID(), book2Id)
                    )
                )
            )
        )

    }

//    @Test
//    fun testBorrowBookWithBeyondDuration() {
//        try {
//            UserRepository.data.find { it.id == "123" }!!.borrowBook(
//                BookRepository.data.find { it.id == "1" }!!.instances[0],
//                Duration.ofDays(16)
//            )
//        } catch (e: BorrowBookException) {
//            assert(true)
//        }
//    }
//
//    @Test
//    fun testBorrowBookWithBeyondCategories() {
//        try {
//            UserRepository.data.find { it.id == "123" }!!.borrowBook(
//                BookRepository.data.find { it.id == "2" }!!.instances[0],
//                Duration.ofDays(1)
//            )
//        } catch (e: BorrowBookException) {
//            assert(true)
//        }
//    }
//
//    @Test
//    fun testBorrowBook() {
//        UserRepository.data.find { it.id == "123" }!!.borrowBook(
//            BookRepository.data.find { it.id == "1" }!!.instances[0],
//            Duration.ofDays(1)
//        )
//    }


}