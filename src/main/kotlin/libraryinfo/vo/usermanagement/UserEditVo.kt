package libraryinfo.vo.usermanagement

import libraryinfo.domain.entity.user.usertype.UserType

data class UserEditVo(
    val name: String,
    val password: String,
    val type: UserType
)