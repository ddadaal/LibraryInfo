package libraryinfo.vo.usermanagement

import libraryinfo.domain.entity.user.usertype.*

enum class UserCreationType(val getType: () -> UserType) {
    Admin({ AdminType() }),
    Undergraduate({ UndergraduateType() }),
    Graduate({ GraduateType() }),
    Teacher({ TeacherType() })
}
