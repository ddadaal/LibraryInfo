package libraryinfo.domain.service.systeminit

import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.usertype.AdminType
import libraryinfo.domain.entity.user.usertype.SystemType
import libraryinfo.repository.user.UserRepository
import java.util.*
import kotlin.collections.ArrayList


object SystemInitDomainService {
    val SYS_USER = User(
        UUID.fromString("4ca39b0b-1d31-49c0-b00b-cabbf7f8cacf"),
        "SYS_USER",
        "系统",
        UUID.randomUUID().toString(),
        SystemType(),
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
        val admins = UserRepository.data.filter { it.isAdmin }

        UserRepository.data.filter { !it.isAdmin }.forEach { user ->
            admins.forEach { admin ->
                user.registerProfileChange(admin)
            }
        }
    }
}