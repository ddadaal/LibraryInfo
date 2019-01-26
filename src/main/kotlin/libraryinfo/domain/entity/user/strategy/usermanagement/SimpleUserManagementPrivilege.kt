package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.domain.service.usermanagement.UserManagementDomainService
import libraryinfo.vo.usermanagement.BorrowReportVo
import libraryinfo.vo.usermanagement.PenaltyPaymentVo
import libraryinfo.vo.usermanagement.UserCreationVo

class SimpleUserManagementPrivilege(
    override var canSearchUser: Boolean,
    override var canCreateUser: Boolean,
    override var canGenerateReport: Boolean,
    override var canGeneratePenaltyPayment: Boolean
) : UserManagementPrivilege
