package libraryinfo.domain.service.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.repository.user.UserRepository
import libraryinfo.util.Id
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import kotlin.collections.ArrayList

object UserManagementDomainService {
    fun searchUser(query: String): List<User> {
        return UserRepository.data.filter {
            it.name == query
                    || it.username == query
                    || it.id.toString().contains(query)
        }
    }

    fun createUser(info: UserCreationVo) {
        UserRepository.data.add(
            User(
                Id(),
                info.username,
                info.name,
                info.rolePreset.role,
                info.password,
                info.rolePreset.getPrivilege(),
                ArrayList(),
                ArrayList(),
                ArrayList()
            )
        )
        UserRepository.save()
    }

    fun generatePenaltyPayment(user: User): PenaltyPaymentVo {
        return PenaltyPaymentVo(user.borrowRecords.filter { it.returnTime == null })
    }

    fun generateReport(user: User): BorrowReportVo {

        return BorrowReportVo(user.borrowRecords)


    }


}
