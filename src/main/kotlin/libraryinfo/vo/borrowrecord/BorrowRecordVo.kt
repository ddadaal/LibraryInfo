package libraryinfo.vo.borrowrecord

import libraryinfo.util.Id
import java.io.Serializable
import java.time.Duration
import java.time.LocalDateTime

class BorrowRecordVo(): Serializable {
    lateinit var borrowTime: LocalDateTime
    lateinit var bookInstanceId: Id
    lateinit var duration: Duration
    var returnTime: LocalDateTime? = null
    lateinit var id: Id


    constructor(borrowTime: LocalDateTime, bookInstanceId: Id, duration: Duration, returnTime: LocalDateTime?): this() {
        this.borrowTime = borrowTime
        this.duration = duration
        this.returnTime = returnTime
        this.id = Id()
        this.bookInstanceId = bookInstanceId
    }
}