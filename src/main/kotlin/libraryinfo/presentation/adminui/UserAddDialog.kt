package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXComboBox
import com.jfoenix.controls.JFXTextField
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import libraryinfo.appservice.usermanagement.UserManagementAppService
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.vo.usermanagement.UserCreationVo
import libraryinfo.vo.usermanagement.UserRolePreset


class UserAddDialog(val onClose: () -> Unit, title: String, content: String) : PromptDialogHelper(title, content) {
    init {

        val tfName = JFXTextField()
        val tfUsername = JFXTextField()
        val tfPassword = JFXTextField()
        val cbUserType = JFXComboBox<UserRolePreset>()

        val service = UserManagementAppService.service

        initializeComboBox(cbUserType)

        cbUserType.value = cbUserType.items[0]

        val container = VBox(
            HBox(Label("用户名"), tfUsername),
            HBox(Label("名字"), tfName),
            HBox(Label("用户类型"), cbUserType),
            HBox(Label("密码"), tfPassword)
        )

        addVBox(container)

        addButton("确认修改", "") {
            if (tfName.text != "" && tfUsername.text != ""
                && tfPassword.text != null
            ) {
                service.createUser(UserCreationVo(
                    tfUsername.text,
                    tfName.text,
                    tfPassword.text,
                    cbUserType.value
                ))
                Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
                onClose()
            }
        }
    }


}