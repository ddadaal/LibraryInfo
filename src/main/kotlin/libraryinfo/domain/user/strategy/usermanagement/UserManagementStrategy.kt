package libraryinfo.domain.user.strategy.usermanagement

import libraryinfo.domain.user.User

interface UserManagementStrategy {
    fun searchUser(userId: String): User
    fun createUser(user: User)
}