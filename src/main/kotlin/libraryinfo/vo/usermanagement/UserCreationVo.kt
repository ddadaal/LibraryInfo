package libraryinfo.vo.usermanagement

data class UserCreationVo (
    val username: String,
    val password: String,
    val type: UserCreationType
)