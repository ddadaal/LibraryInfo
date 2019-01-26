package libraryinfo.repository

import javafx.application.Application
import libraryinfo.Client
import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.doc.Pdf
import libraryinfo.domain.entity.book.doc.Txt
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.usertype.AdminType
import libraryinfo.domain.entity.user.usertype.UndergraduateType
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import org.junit.jupiter.api.Test
import java.time.Duration

import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class RepositoryTest {

    @Test
    fun insertInitialData() {
        val userId = UUID.randomUUID()
        val book1Id = UUID.randomUUID()
        val book2Id = UUID.randomUUID()
        val bookInstance = BookInstance(UUID.randomUUID(), book1Id)


        BookRepository.data.clear()
        UserRepository.data.clear()

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
                                        bookInstance,
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


        UserRepository.data.find { it.type is UndergraduateType }!!.borrowBook(bookInstance)
        println(bookInstance.id)
        println("----------")
        BookRepository.data.forEach { it.instances.forEach { i -> println(i.id) } }
        println ("-------------")
        UserRepository.data.find { !it.isAdmin }!!.borrowRecords.forEach { println(it.bookInstanceId) }
        Application.launch(Client::class.java, "")
    }

}