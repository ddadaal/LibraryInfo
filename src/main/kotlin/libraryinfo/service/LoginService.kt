package libraryinfo.service

import libraryinfo.entity.user.User
import libraryinfo.repository.user.UserRepository

object LoginService {

    var currentUser: User? = null
        private set

    fun login(username: String, password: String): Boolean {

        currentUser = UserRepository.data.find { it.username == username && it.password == password }
        return currentUser != null
    }
}
