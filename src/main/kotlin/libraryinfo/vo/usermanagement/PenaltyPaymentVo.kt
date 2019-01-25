package libraryinfo.vo.usermanagement

import libraryinfo.vo.borrowrecord.BorrowRecordVo
import java.time.Duration
import java.time.LocalDateTime


data class PenaltyPaymentVo(
        var penaltyRecords: List<BorrowRecordVo>
) {
    var fee: Double = 0.0
        get() = penaltyRecords.filter {
            it.returnTime == null &&
                    (LocalDateTime.now()).isAfter(it.borrowTime.plusDays(it.duration.toDays()))
        }
                .sumByDouble {
                    Duration.between(LocalDateTime.now(), it.borrowTime.plusDays(it.duration.toDays()))
                            .abs()
                            .toDays() * 1.0
                }
}