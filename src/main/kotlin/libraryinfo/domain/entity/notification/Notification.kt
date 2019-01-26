package libraryinfo.domain.entity.notification

import libraryinfo.domain.entity.user.User
import libraryinfo.repository.user.UserRepository
import libraryinfo.util.Id
import java.io.Serializable
import java.time.LocalDateTime


class Notification() : Serializable {
    lateinit var id: Id
    var read: Boolean = false
    lateinit var date: LocalDateTime
    lateinit var senderId: Id
    lateinit var content: String

    val sender: User
        get() = UserRepository.data.find { it.id == senderId }!!

    constructor(date: LocalDateTime, senderId: Id, content: String) : this() {
        this.date = date
        this.senderId = senderId
        this.content = content
        this.id = Id()
    }

    fun read() {
        read = true
        UserRepository.save()
    }
}
