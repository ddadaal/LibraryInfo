package libraryinfo.vo.usermanagement

import libraryinfo.vo.borrowrecord.BorrowRecordVo
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

data class PenaltyItem(private var record: BorrowRecordVo) {
    var bookId: String = record.bookInstanceId.short
    var borrowDate: LocalDate = record.borrowTime.toLocalDate()
    var day = 0
        get() {
            return Duration.between(LocalDateTime.now(), record.borrowTime.plusDays(record.duration.toDays()))
                .abs()
                .toDays()
                .toInt()
        }

}


data class PenaltyPaymentVo(private var items: List<BorrowRecordVo>) {
    val records: List<PenaltyItem> = items.map { PenaltyItem(it) }
    var fee: Double = 0.0
        get() = records.sumByDouble { it.day * 1.0 }
}