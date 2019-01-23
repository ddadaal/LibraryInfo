package libraryinfo.entity.borrowrecord

import java.io.Serializable
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

class BorrowRecord(): Serializable {
    lateinit var borrowTime: LocalDateTime
    lateinit var bookId: String
    lateinit var duration: Duration
    var returnTime: LocalDateTime? = null
    lateinit var id: String

    constructor(borrowTime: LocalDateTime, bookId: String, duration: Duration, returnTime: LocalDateTime?): this() {
        this.borrowTime = borrowTime
        this.bookId = bookId
        this.duration = duration
        this.returnTime = returnTime
        this.id = UUID.randomUUID().toString()
    }
}