package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXTextField
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.vo.usermanagement.UserInfoVo

class UserInfoUiController : UiController {

    lateinit var nameInput: JFXTextField
    lateinit var passwordInput: JFXTextField

    val userInfo: UserInfoVo?
        get() =
            if (nameInput.text != "" && passwordInput.text != "") UserInfoVo(nameInput.text, passwordInput.text)
            else null

    override fun load(): UiElement {
        return doLoad("/fxml/adminui/UserInfo.fxml")
    }

    fun setUser(user: User) {
        nameInput.text = user.name
        passwordInput.text = user.password
    }


}