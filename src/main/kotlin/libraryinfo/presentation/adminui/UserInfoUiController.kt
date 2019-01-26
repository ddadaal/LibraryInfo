package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXComboBox
import com.jfoenix.controls.JFXTextField
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.role.UserRole
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.vo.usermanagement.UserEditVo
import libraryinfo.vo.usermanagement.UserRolePreset


class UserInfoUiController : UiController {

    lateinit var tbUsername: JFXTextField
    lateinit var nameInput: JFXTextField
    lateinit var passwordInput: JFXTextField
    lateinit var cbType: JFXComboBox<UserRolePreset>

    fun initialize() {
        initializeComboBox(cbType)
    }

    val userInfo: UserEditVo?
        get() =
            if (nameInput.text != "" && passwordInput.text != "")
                UserEditVo(
                    nameInput.text,
                    passwordInput.text,
                    cbType.selectionModel.selectedItem
                )
            else null

    override fun load(): UiElement {
        return doLoad("/fxml/adminui/UserInfo.fxml")
    }

    fun init(user: User, allowEditType: Boolean) {
        tbUsername.text =user.username
        nameInput.text = user.name
        passwordInput.text = user.password
        cbType.value = UserRolePreset.values().find { it.role == user.role }
        cbType.isDisable = !allowEditType
    }


}