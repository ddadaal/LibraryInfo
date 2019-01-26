package libraryinfo.domain.entity.user.role

import java.io.Serializable

class UserRole(var name: String): Serializable {
    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserRole) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    companion object {
        val Admin = UserRole("管理员")
        val Undergraduate = UserRole("本科生")
        val Graduate = UserRole("研究生")
        val Teacher = UserRole("老师")
        val System = UserRole("系统")
    }

}