package libraryinfo.appservice.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.usermanagement.UserManagementDomainService
import libraryinfo.vo.usermanagement.UserCreationVo

interface UserManagementAppService {
    fun searchUser(query: String): List<User>
    fun createUser(info: UserCreationVo)
}
