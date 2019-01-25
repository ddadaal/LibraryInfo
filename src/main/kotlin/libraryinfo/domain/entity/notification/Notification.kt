package libraryinfo.domain.entity.notification

import libraryinfo.domain.entity.user.User
import libraryinfo.repository.user.UserRepository
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*


class Notification() : Serializable {
    lateinit var id: UUID
    var read: Boolean = false
    lateinit var date: LocalDateTime
    lateinit var senderId: UUID
    lateinit var content: String

    val sender: User
        get() = UserRepository.data.find { it.id == senderId }!!

    constructor(date: LocalDateTime, senderId: UUID, content: String) : this() {
        this.date = date
        this.senderId = senderId
        this.content = content
        this.id = UUID.randomUUID()
    }

    fun read() {
        read = true
        UserRepository.save()
    }
}
