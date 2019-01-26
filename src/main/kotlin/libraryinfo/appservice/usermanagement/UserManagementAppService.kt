package libraryinfo.appservice.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import java.util.*

interface UserManagementAppService {
    fun searchUser(query: String): List<User>
    fun createUser(info: UserCreationVo)
    fun generateReport(userId: UUID): BorrowReportVo
    fun generatePenaltyPayment(userId: UUID): PenaltyPaymentVo

    companion object {
        val service: UserManagementAppService = UserManagementAppServiceImpl()
    }
}
