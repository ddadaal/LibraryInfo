package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.entity.user.User
import java.io.Serializable

interface UserManagementStrategy: Serializable {
    fun searchUser(query: String): User?
    fun createUser(user: User)
}