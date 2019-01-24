package libraryinfo.domain.service.usermanagement

import libraryinfo.domain.entity.user.User
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.usermanagement.UserCreationVo

object UserManagementDomainService {
    fun searchUser(query: String): User? {
        return UserRepository.data.find { it.name ==query ||it.username ==query || it.id == query }
    }

    fun createUser(info: UserCreationVo) {
        UserRepository.data.add(User(
                ((UserRepository.data.size +1).toString()),
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
