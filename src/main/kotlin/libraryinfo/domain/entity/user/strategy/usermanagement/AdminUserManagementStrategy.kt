package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.usermanagement.UserManagementDomainService

object AdminUserManagementStrategy: UserManagementStrategy {
    override fun searchUser(query: String): User? {
        return UserManagementDomainService.searchUser(query)
    }

    override fun createUser(user: User) {
        return UserManagementDomainService.createUser(user)
    }

}