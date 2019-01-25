package libraryinfo.domain.service.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import java.util.*

object UserManagementDomainService {
    fun searchUser(query: String): List<User> {
        return UserRepository.data.filter { it.name == query || it.username == query || it.id.toString().contains(query) }
    }

    fun createUser(info: UserCreationVo) {
        UserRepository.data.add(User(
                UUID.randomUUID(),
                info.username,
                info.username,
                info.password,
                info.type.getType(),
                ArrayList(),
                ArrayList(),
                ArrayList()
        ))
        UserRepository.save()
    }

    fun generatePenaltyPayment(userId: UUID): PenaltyPaymentVo {
        return PenaltyPaymentVo(
                UserRepository.data.find { it.id == userId }!!.borrowRecords.filter { it.returnTime == null }
        )
    }

    fun generateReport(userId: UUID): BorrowReportVo {

        return BorrowReportVo(
                UserRepository.data.find { it.id == userId }!!.borrowRecords
        )


    }


}
