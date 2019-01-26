package libraryinfo.vo.usermanagement

import libraryinfo.domain.entity.user.role.UserRole
import libraryinfo.domain.entity.user.userprivilege.*

enum class UserRolePreset (
    val role: UserRole,
    val getPrivilege: () -> UserPrivilege
) {
    Admin(UserRole.Admin, { AdminPrivilege() }),
    Undergraduate(UserRole.Undergraduate, { UndergraduatePrivilege() }),
    Graduate(UserRole.Graduate, { GraduatePrivilege() }),
    Teacher(UserRole.Teacher, { TeacherPrivilege() });

    override fun toString(): String {
        return role.name
    }
}

data class UserCreationVo (
    val username: String,
    val name: String,
    val password: String,
    val rolePreset: UserRolePreset
)