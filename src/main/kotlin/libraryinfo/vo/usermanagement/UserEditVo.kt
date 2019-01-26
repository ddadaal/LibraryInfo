package libraryinfo.vo.usermanagement

import libraryinfo.domain.entity.user.userprivilege.UserPrivilege

data class UserEditVo(
    val name: String,
    val password: String,
    val rolePreset: UserRolePreset
)