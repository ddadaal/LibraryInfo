package libraryinfo.appservice.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.usermanagement.UserManagementDomainService

interface UserManagementAppService {
    fun searchUser(query: String): User?
    fun createUser(user: User)
}
