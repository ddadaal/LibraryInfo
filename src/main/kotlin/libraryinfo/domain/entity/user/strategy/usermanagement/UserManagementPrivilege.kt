package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo
import java.io.Serializable

interface UserManagementPrivilege: Serializable {

    var canSearchUser: Boolean

    var canCreateUser: Boolean

    var canGenerateReport: Boolean

    var canGeneratePenaltyPayment: Boolean
}