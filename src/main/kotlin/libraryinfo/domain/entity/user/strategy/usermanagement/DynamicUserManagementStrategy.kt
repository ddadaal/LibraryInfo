package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.domain.service.usermanagement.UserManagementDomainService
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import java.util.*

class DynamicUserManagementStrategy(
        var canCreate: Boolean = false,
        var canSearch: Boolean = false,
        var canGenerateReport: Boolean = false,
        var canGeneratePayment: Boolean = false
) : UserManagementStrategy {
    override fun generateReport(userId: UUID): BorrowReportVo {
        if (!canGenerateReport) {
            throw PermissionDeniedException()
        }
        return BorrowReportVo(
                UserRepository.data.find { it.id == userId }!!.borrowRecords
        )


    }

    override fun generatePenaltyPayment(userId: UUID): PenaltyPaymentVo {
        if (!canGeneratePayment) {
            throw PermissionDeniedException()
        }
        return PenaltyPaymentVo(
                UserRepository.data.find { it.id == userId }!!.borrowRecords.filter { it.returnTime == null }
        )
    }


    override fun searchUser(query: String): List<User> {
        if (!canSearch) {
            throw PermissionDeniedException()
        }
        return UserManagementDomainService.searchUser(query)

    }

    override fun createUser(info: UserCreationVo) {
        if (!canCreate) {
            throw PermissionDeniedException()
        }
        return UserManagementDomainService.createUser(info)
    }

}
