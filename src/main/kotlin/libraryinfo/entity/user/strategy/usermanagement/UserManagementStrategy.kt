package libraryinfo.entity.user.strategy.usermanagement

import libraryinfo.entity.user.User
import java.io.Serializable

interface UserManagementStrategy: Serializable {
    fun searchUser(userId: String): User
    fun createUser(user: User)
}