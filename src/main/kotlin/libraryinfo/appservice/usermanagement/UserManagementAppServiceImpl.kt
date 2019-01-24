package libraryinfo.appservice.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.usermanagement.UserManagementDomainService

class UserManagementAppServiceImpl: UserManagementAppService {
    override fun searchUser(query: String): User? {
        return UserManagementDomainService.searchUser(query)
    }

    override fun createUser(user: User) {
        UserManagementDomainService.createUser(user)
    }
}
