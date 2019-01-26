package libraryinfo.appservice.auth

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.userprivilege.UserPrivilege
import libraryinfo.domain.exception.PermissionDeniedException

interface AuthAppService {
    val currentUser: User?

    fun login(username: String, password: String): Boolean


    companion object {
        val SERVICE: AuthAppService = AuthAppServiceImpl()
        val currentUser: User?
            get() = SERVICE.currentUser
    }
}

fun checkPrivilege(getPrivilege: (UserPrivilege) -> Boolean) {
    val currentUser = AuthAppService.SERVICE.currentUser

    if (!(currentUser != null && getPrivilege(currentUser.privilege))) {
        throw PermissionDeniedException()
    }

}