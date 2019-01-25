package libraryinfo.appservice.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.usermanagement.UserManagementDomainService
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import java.util.*

class UserManagementAppServiceImpl: UserManagementAppService {
    override fun generateReport(userId: UUID): BorrowReportVo {
       return UserManagementDomainService.generateReport(userId)
    }

    override fun generatePenaltyPayment(userId: UUID): PenaltyPaymentVo {
       return UserManagementDomainService.generatePenaltyPayment(userId)
    }

    override fun searchUser(query: String): List<User> {
        return UserManagementDomainService.searchUser(query)
    }

    override fun createUser(info: UserCreationVo) {
        UserManagementDomainService.createUser(info)
    }
}
