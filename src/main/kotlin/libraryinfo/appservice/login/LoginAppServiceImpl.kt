package libraryinfo.appservice.login

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.service.systeminit.SystemInitDomainService
import libraryinfo.repository.user.UserRepository

class LoginAppServiceImpl: LoginAppService {

    override var currentUser: User? = null
        private set

    override fun login(username: String, password: String): Boolean {

        currentUser = UserRepository.data.find { it.username == username && it.password == password }

        // prohibit system user from logging in
        if (currentUser == SystemInitDomainService.SYS_USER) {
            currentUser = null
        }

        return currentUser != null
    }
}
