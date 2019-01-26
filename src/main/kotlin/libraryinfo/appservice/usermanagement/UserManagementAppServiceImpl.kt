package libraryinfo.appservice.usermanagement

import libraryinfo.appservice.auth.checkPrivilege
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.exception.NoSuchUserException
import libraryinfo.domain.service.usermanagement.UserManagementDomainService
import libraryinfo.repository.user.UserRepository
import libraryinfo.util.Id
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import java.util.*

class UserManagementAppServiceImpl: UserManagementAppService {
    override fun generateReport(userId: UUID): BorrowReportVo {

        checkPrivilege { it.userManagementPrivilege.canGenerateReport }

        val user = UserRepository.data.find { it.id == Id(userId) } ?: throw NoSuchUserException()
       return UserManagementDomainService.generateReport(user)
    }

    override fun generatePenaltyPayment(userId: UUID): PenaltyPaymentVo {

        checkPrivilege { it.userManagementPrivilege.canGeneratePenaltyPayment }

        val user = UserRepository.data.find { it.id == Id(userId) } ?: throw NoSuchUserException()

        return UserManagementDomainService.generatePenaltyPayment(user)
    }

    override fun searchUser(query: String): List<User> {

        checkPrivilege { it.userManagementPrivilege.canSearchUser }

        return UserManagementDomainService.searchUser(query)
    }

    override fun createUser(info: UserCreationVo) {

        checkPrivilege { it.userManagementPrivilege.canCreateUser }

        UserManagementDomainService.createUser(info)
    }
}
