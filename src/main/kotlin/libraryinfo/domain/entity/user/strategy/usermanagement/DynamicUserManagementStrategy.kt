package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.domain.service.usermanagement.UserManagementDomainService

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

    override fun createUser(user: User) {
        if (!canCreate) {
            throw PermissionDeniedException()
        }
        return UserManagementDomainService.createUser(user)
    }

}
