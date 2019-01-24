package libraryinfo.repository.user

import libraryinfo.entity.user.User
import libraryinfo.repository.Repository

object UserRepository: Repository<ArrayList<User>>("users", ArrayList()) {
    init {
        // register admin to all user's profile change
        val admins = data.filter { it.isAdmin }

        data.filter { !it.isAdmin }.forEach { user ->
            admins.forEach { admin ->
                user.registerProfileChange(admin)
            }
        }
    }
}