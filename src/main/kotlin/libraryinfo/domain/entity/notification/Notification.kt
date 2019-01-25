package libraryinfo.domain.entity.notification

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
