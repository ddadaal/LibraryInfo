package libraryinfo.domain.service.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.usermanagement.UserCreationVo
import java.util.*

object UserManagementDomainService {
    fun searchUser(query: String): List<User> {
        return UserRepository.data.filter { it.name ==query ||it.username ==query || it.id.toString().contains(query) }
    }

    fun createUser(info: UserCreationVo) {
        UserRepository.data.add(User(
                UUID.randomUUID(),
                info.username,
                info.username,
                info.password,
                info.type.getType(),
                ArrayList(),
                ArrayList(),
                ArrayList()
        ))
        UserRepository.save()
    }
}
