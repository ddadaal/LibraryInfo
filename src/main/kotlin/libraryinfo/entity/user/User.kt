package libraryinfo.entity.user

import libraryinfo.entity.book.Book
import libraryinfo.entity.borrowrecord.BorrowRecord
import libraryinfo.entity.notification.Notification
import libraryinfo.entity.user.usertype.UserType
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import java.io.Serializable
import java.time.Duration
import java.time.LocalDateTime

class User() : Serializable {

    lateinit var id: String
    lateinit var username: String
    lateinit var name: String
    lateinit var password: String
    lateinit var type: UserType
    lateinit var notifications: ArrayList<Notification>
    lateinit var borrowRecords: ArrayList<BorrowRecord>

    val mainUiElement: UiElement
        get() = type.mainUiElement

    val unreadNotification: List<Notification>
        get() = notifications.filter { !it.read }

    constructor(
        id: String,
        username: String,
        name: String,
        password: String,
        type: UserType,
        notifications: ArrayList<Notification>,
        borrowRecords: ArrayList<BorrowRecord>
    ): this() {
        this.username = username
        this.id = id
        this.name = name
        this.password = password
        this.type = type
        this.notifications = notifications
        this.borrowRecords = borrowRecords
    }

    fun borrowBook(book: Book, duration: Duration) {
        if (type.borrowStrategy.canBorrowBook(book, duration)) {
            val currentTime = LocalDateTime.now()
            val record = BorrowRecord(currentTime, book.id, duration, null)
            borrowRecords.add(record)

            book.beBorrowed(record.id)

            UserRepository.save()
        }
    }

    fun returnBook(book: Book) {
        val record = borrowRecords.find { it.id == book.borrowRecordId }
        if (record != null) {
            record.returnTime = LocalDateTime.now()

            book.beReturned()

            UserRepository.save()
        }
    }

    fun updateInformation() {
        UserRepository.save()
    }

    fun notify(content: String, sender: User) {
        this.notifications.add(Notification(LocalDateTime.now(), sender.id, content))
        UserRepository.save()
    }

}
