package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.repository.user.UserRepository

class UserInfoEditingDialog(val user: User, val onClose: () -> Unit, title: String, content: String) : PromptDialogHelper(title, content) {

    init {
        val element = UserInfoUiController().load()
        setContent(element.component)
        val controller = element.getController<UserInfoUiController>()
        controller.setUser(user)
        addButton("确认修改", "") {
            val userInfo = controller.userInfo
            if (userInfo != null) {
                user.name = userInfo.name
                user.password = userInfo.password
                UserRepository.save()
                Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
                onClose()
            }
        }
    }


}