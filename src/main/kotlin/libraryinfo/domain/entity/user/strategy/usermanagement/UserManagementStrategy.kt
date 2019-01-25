package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import java.io.Serializable
import java.util.*

interface UserManagementStrategy: Serializable {
    fun searchUser(query: String): List<User>
    fun createUser(info: UserCreationVo)
    fun generateReport(userId:UUID): BorrowReportVo
    fun generatePenaltyPayment(userId: UUID): PenaltyPaymentVo
}