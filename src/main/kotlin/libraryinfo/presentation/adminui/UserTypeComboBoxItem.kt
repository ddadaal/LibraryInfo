package libraryinfo.presentation.adminui

import javafx.scene.control.ComboBox
import libraryinfo.domain.entity.user.userprivilege.*
import libraryinfo.vo.usermanagement.UserRolePreset

fun initializeComboBox(cbType: ComboBox<UserRolePreset>) {
    cbType.items.addAll(UserRolePreset.values())
}