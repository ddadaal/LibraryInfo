package libraryinfo.domain.entity.user

import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.usertype.UserType
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.usermanagement.UserEditVo
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import java.io.IOException
import kotlin.collections.ArrayList


class User() : Serializable, ProfileChangeObserver {


    lateinit var id: UUID
    lateinit var username: String
    lateinit var name: String
    lateinit var password: String
    lateinit var type: UserType
    lateinit var notifications: ArrayList<Notification>
    lateinit var ownedBookInstances: ArrayList<BookInstance>
    lateinit var borrowRecords: ArrayList<BorrowRecordVo>

    @Transient
    private var profileChangeObservers = ArrayList<ProfileChangeObserver>()

    val mainUiElement: UiElement
        get() = type.mainUiElement

    val unreadNotification: List<Notification>
        get() = notifications.filter { !it.read }



    constructor(
            id: UUID,
            username: String,
            name: String,
            password: String,
            type: UserType,
            notifications: ArrayList<Notification>,
            borrowRecords: ArrayList<BorrowRecordVo>,
            ownedBookInstances: ArrayList<BookInstance>
    ) : this() {
        this.username = username
        this.id = id
        this.name = name
        this.password = password
        this.type = type
        this.notifications = notifications
        this.borrowRecords = borrowRecords
        this.ownedBookInstances = ownedBookInstances
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(`in`: java.io.ObjectInputStream) {
        `in`.defaultReadObject()
        profileChangeObservers = ArrayList<ProfileChangeObserver>()
    }

    fun borrowBook(bookInstance: BookInstance): UUID {
        if (type.borrowStrategy.canBorrowBook(bookInstance.book)) {

            val currentTime = LocalDateTime.now()
            val record = BorrowRecordVo(currentTime, bookInstance.id, type.borrowStrategy.maxDuration, null)
            borrowRecords.add(record)

            bookInstance.borrowBook(record.id)
            ownedBookInstances.add(bookInstance)

            UserRepository.save()
            return record.id
        }
        else throw PermissionDeniedException()

    }

    fun returnBook(bookInstance: BookInstance) {
        val record = borrowRecords.find { it.bookInstanceId == bookInstance.id }
        if (record != null) {
            record.returnTime = LocalDateTime.now()

            bookInstance.returnBook()
            ownedBookInstances.remove(bookInstance)

            UserRepository.save()
        }
    }

    fun updateInformation(info: UserEditVo) {
        this.name = info.name
        this.password = info.password
        this.type = info.type
        val time = LocalDateTime.now()
        profileChangeObservers.forEach { it.onProfileChange(this, time) }

        UserRepository.save()
    }

    fun registerProfileChange(observer: ProfileChangeObserver) {
        profileChangeObservers.add(observer)
    }

    override fun onProfileChange(user: User, time: LocalDateTime) {
        this.notifications.add(Notification(LocalDateTime.now(), user.id, "${user.name}改了用户信息"))
        UserRepository.save()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    val isAdmin: Boolean
        get() = type.isAdmin

}
