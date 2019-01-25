package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.vo.usermanagement.UserCreationVo
import java.io.Serializable

interface UserManagementStrategy: Serializable {
    fun searchUser(query: String): List<User>
    fun createUser(info: UserCreationVo)
}