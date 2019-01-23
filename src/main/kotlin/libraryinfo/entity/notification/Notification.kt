package libraryinfo.entity.notification

import libraryinfo.repository.user.UserRepository
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*


class Notification() : Serializable {
    lateinit var id: String
    var read: Boolean = false
    lateinit var date: LocalDateTime
    lateinit var senderId: String
    lateinit var content: String

    constructor(date: LocalDateTime, senderId: String, content: String) : this() {
        this.date = date
        this.senderId = senderId
        this.content = content
        this.id = UUID.randomUUID().toString()
    }

    fun read() {
        read = true
        UserRepository.save()
    }
}
