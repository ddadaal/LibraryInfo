package libraryinfo.appservice.login

import libraryinfo.domain.entity.user.User

interface LoginAppService {
    val currentUser: User?

    fun login(username: String, password: String): Boolean
}