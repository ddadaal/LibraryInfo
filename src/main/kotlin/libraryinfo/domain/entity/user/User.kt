package libraryinfo.domain.entity.user

import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.entity.user.role.UserRole
import libraryinfo.domain.entity.user.userprivilege.UserPrivilege
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import libraryinfo.util.Id
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import libraryinfo.vo.usermanagement.UserEditVo
import java.io.IOException
import java.io.Serializable
import java.time.LocalDateTime


class User() : Serializable, ProfileChangeObserver {


    lateinit var id: Id
    lateinit var username: String
    lateinit var name: String
    lateinit var role: UserRole
    lateinit var password: String
    lateinit var privilege: UserPrivilege
    lateinit var notifications: ArrayList<Notification>
    lateinit var ownedBookInstanceIds: ArrayList<Id>
    lateinit var borrowRecords: ArrayList<BorrowRecordVo>

    @Transient
    private var profileChangeObservers = ArrayList<ProfileChangeObserver>()

    val unreadNotification: List<Notification>
        get() = notifications.filter { !it.read }

    var ownedBookInstances: List<BookInstance> = emptyList()
        get() {
            return ownedBookInstanceIds.map {
                BookRepository.data.find { b -> b.instances.find { i -> i.id == it } != null }!!.instances.find { i -> i.id == it }!!
            }
        }




    constructor(
        id: Id,
        username: String,
        name: String,
        role: UserRole,
        password: String,
        privilege: UserPrivilege,
        notifications: ArrayList<Notification>,
        borrowRecords: ArrayList<BorrowRecordVo>,
        ownedBookInstanceIds: ArrayList<Id>
    ) : this() {
        this.username = username
        this.id = id
        this.name = name
        this.role = role
        this.password = password
        this.privilege = privilege
        this.notifications = notifications
        this.borrowRecords = borrowRecords
        this.ownedBookInstanceIds = ownedBookInstanceIds
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(`in`: java.io.ObjectInputStream) {
        `in`.defaultReadObject()
        profileChangeObservers = ArrayList()
    }

    fun borrowBook(bookInstance: BookInstance): Id {
        val borrowability = privilege.borrowPrivilege.judgeBookBorrowability(this, bookInstance.book)
        if (borrowability.canBorrow) {

            val currentTime = LocalDateTime.now()
            val record = BorrowRecordVo(currentTime, bookInstance.id, privilege.borrowPrivilege.maxDuration, null)
            borrowRecords.add(record)

            bookInstance.borrowBook(record.id)
            ownedBookInstanceIds.add(bookInstance.id)

            UserRepository.save()
            return record.id
        }
        else throw PermissionDeniedException(borrowability.error)

    }

    fun returnBook(bookInstance: BookInstance) {
        val record = borrowRecords.find { it.bookInstanceId == bookInstance.id }
        if (record != null) {
            record.returnTime = LocalDateTime.now()

            bookInstance.returnBook()
            ownedBookInstanceIds.remove(bookInstance.id)

            UserRepository.save()
        }
    }

    fun updateInformation(info: UserEditVo) {
        this.name = info.name
        this.password = info.password
        this.privilege = info.rolePreset.getPrivilege()
        this.role = info.rolePreset.role
        val time = LocalDateTime.now()
        profileChangeObservers.forEach { it.onProfileChange(this, time) }

        UserRepository.save()
    }

    fun registerProfileChange(observer: ProfileChangeObserver) {
        profileChangeObservers.add(observer)
    }

    fun notify(sender: User, content: String) {
        this.notifications.add(Notification(LocalDateTime.now(), sender.id, content))
    }

    override fun onProfileChange(user: User, time: LocalDateTime) {
        notify(user, "${user.name}改了用户信息")
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

}
