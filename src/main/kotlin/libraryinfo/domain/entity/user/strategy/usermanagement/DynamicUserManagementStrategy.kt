package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.domain.service.usermanagement.UserManagementDomainService
import libraryinfo.vo.usermanagement.UserCreationVo

class DynamicUserManagementStrategy(
    var canCreate: Boolean,
    var canSearch: Boolean
): UserManagementStrategy {

    override fun searchUser(query: String): User? {
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
