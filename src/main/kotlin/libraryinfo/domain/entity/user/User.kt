package libraryinfo.domain.entity.user

import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.usertype.UserType
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.user.UserRepository
import java.io.Serializable
import java.time.Duration
import java.time.LocalDateTime


class User() : Serializable, ProfileChangeObserver {


    lateinit var id: String
    lateinit var username: String
    lateinit var name: String
    lateinit var password: String
    lateinit var type: UserType
    lateinit var notifications: ArrayList<Notification>
    lateinit var ownedBookInstances: ArrayList<BookInstance>
    lateinit var borrowRecords: ArrayList<BorrowRecordVo>

    private var profileChangeObservers = ArrayList<ProfileChangeObserver>()

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
        borrowRecords: ArrayList<BorrowRecordVo>,
        ownedBookInstances: ArrayList<BookInstance>
    ): this() {
        this.username = username
        this.id = id
        this.name = name
        this.password = password
        this.type = type
        this.notifications = notifications
        this.borrowRecords = borrowRecords
        this.ownedBookInstances = ownedBookInstances
    }

    fun borrowBook(bookInstance: BookInstance, duration: Duration) {
        if (type.borrowStrategy.canBorrowBook(bookInstance.book, duration)) {

            val currentTime = LocalDateTime.now()
            val record = BorrowRecordVo(currentTime, bookInstance.id, duration, null)
            borrowRecords.add(record)

            bookInstance.borrowBook()
            ownedBookInstances.add(bookInstance)

            UserRepository.save()
        }
    }

    fun returnBook(instance: BookInstance) {
        val record = borrowRecords.find { it.bookInstanceId == instance.id }
        if (record != null) {
            record.returnTime = LocalDateTime.now()

            instance.returnBook()

            UserRepository.save()
        }
    }

    fun updateInformation() {

        val time = LocalDateTime.now()
        profileChangeObservers.forEach { it.onProfileChange(this, time )}

        UserRepository.save()
    }

    fun registerProfileChange(observer: ProfileChangeObserver) {
        profileChangeObservers.add(observer)
    }

    override fun onProfileChange(user: User, time: LocalDateTime) {
        this.notifications.add(Notification(LocalDateTime.now(), user.id, "${user.name}改了用户信息"))
        UserRepository.save()
    }

    val isAdmin: Boolean
        get() = type.isAdmin

}
