package libraryinfo.domain.entity.user.strategy.usermanagement

import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.domain.entity.user.User

object NoUserManagementStrategy: UserManagementStrategy {
    override fun searchUser(userId: String): User {
        throw PermissionDeniedException()
    }

    override fun createUser(user: User) {
        throw PermissionDeniedException()
    }

}