package libraryinfo.appservice.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.usermanagement.UserManagementDomainService
import libraryinfo.vo.usermanagement.UserCreationVo

class UserManagementAppServiceImpl: UserManagementAppService {
    override fun searchUser(query: String): List<User> {
        return UserManagementDomainService.searchUser(query)
    }

    override fun createUser(info: UserCreationVo) {
        UserManagementDomainService.createUser(info)
    }
}
