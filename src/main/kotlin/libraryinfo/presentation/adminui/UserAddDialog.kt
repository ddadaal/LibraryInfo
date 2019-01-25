package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXCheckBox
import com.jfoenix.controls.JFXComboBox
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import javafx.collections.FXCollections
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.util.Callback
import libraryinfo.domain.entity.user.User
import libraryinfo.domain.entity.user.usertype.*
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.repository.user.UserRepository
import java.util.*
import kotlin.collections.ArrayList

class UserAddDialog(val onClose: () -> Unit, title: String, content: String) : PromptDialogHelper(title, content) {
    init {

        val nameInput = JFXTextField()
        val usernameInput = JFXTextField()
        val passwordInput = JFXTextField()
        val userTypeInput = JFXComboBox<UserType>(
                FXCollections.observableArrayList(
                        UndergraduateType(), GraduateType(), TeacherType(), AdminType())
        )
        userTypeInput.value = userTypeInput.items[0]

        val container = VBox(
                HBox(Label("用户名"), usernameInput),
                HBox(Label("名字"), nameInput),
                HBox(Label("用户类型"), userTypeInput),
                HBox(Label("密码"), passwordInput)
        )

        addVBox(container)

        addButton("确认修改", "") {
            if (nameInput.text != "" && usernameInput.text != ""
                    && passwordInput.text != null) {
                UserRepository.data.add(
                        User(
                                UUID.randomUUID(),
                                usernameInput.text,
                                nameInput.text,
                                passwordInput.text,
                                userTypeInput.value,
                                ArrayList(),
                                ArrayList(),
                                ArrayList()
                        )
                )
                UserRepository.save()
                Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
                onClose()
            }
        }
    }


}