package libraryinfo.domain.service.systeminit

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.role.UserRole
import libraryinfo.domain.entity.user.userprivilege.SystemPrivilege
import libraryinfo.repository.user.UserRepository
import libraryinfo.util.Id
import java.util.*
import kotlin.collections.ArrayList


object SystemInitDomainService {
    val SYS_USER = User(
        Id("4ca39b0b-1d31-49c0-b00b-cabbf7f8cacf"),
        "SYS_USER",
        "系统",
        UserRole.System,
        UUID.randomUUID().toString(),
        SystemPrivilege(),
        ArrayList(),
        ArrayList(),
        ArrayList()
    )

    fun init() {
        addSystemUserIfNotFound()

        registerProfileChange()
    }

    private fun addSystemUserIfNotFound() {
        if (!UserRepository.data.contains(SYS_USER)) {
            // create a system user
            UserRepository.data.add(SYS_USER)
            UserRepository.save()
        }
    }

    private fun registerProfileChange() {
        val admins = UserRepository.data.filter { it.role == UserRole.Admin }

        UserRepository.data.filter { it.role != UserRole.Admin }.forEach { user ->
            admins.forEach { admin ->
                user.registerProfileChange(admin)
            }
        }
    }
}