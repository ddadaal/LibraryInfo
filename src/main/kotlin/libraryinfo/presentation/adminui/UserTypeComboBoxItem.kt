package libraryinfo.presentation.adminui

import javafx.scene.control.ComboBox
import libraryinfo.domain.entity.user.usertype.*


val allTypes = arrayListOf(
    UndergraduateType(),
    GraduateType(),
    TeacherType(),
    AdminType()
)

class UserTypeComboBoxItem(var type: UserType) {
    override fun toString(): String {
        return type.name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserTypeComboBoxItem) return false

        if (type.name != other.type.name) return false

        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }


}

fun initializeComboBox(cbType: ComboBox<UserTypeComboBoxItem>) {
    cbType.items.addAll(
        allTypes
        .map {
            UserTypeComboBoxItem(it)
        }
    )
}