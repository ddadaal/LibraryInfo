package libraryinfo.vo.borrowrecord

import java.io.Serializable
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

class BorrowRecordVo(): Serializable {
    lateinit var borrowTime: LocalDateTime
    lateinit var bookInstanceId: UUID
    lateinit var duration: Duration
    var returnTime: LocalDateTime? = null
    lateinit var id: UUID

    constructor(borrowTime: LocalDateTime, bookInstanceId: UUID, duration: Duration, returnTime: LocalDateTime?): this() {
        this.borrowTime = borrowTime
        this.duration = duration
        this.returnTime = returnTime
        this.id = UUID.randomUUID()
        this.bookInstanceId = bookInstanceId
    }
}