package libraryinfo.presentation.userui

import com.jfoenix.controls.JFXButton
import javafx.scene.layout.HBox
import libraryinfo.appservice.auth.AuthAppService
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.adminui.UserInfoUiController
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class ProfileManagementUiController : UiController {

    lateinit var modifyButton: JFXButton
    lateinit var container: HBox
    lateinit var controller: UserInfoUiController
    val user: User
        get() = AuthAppService.currentUser!!

    override fun load(): UiElement {
        return doLoad("/fxml/userui/ProfileManagement.fxml")
    }

    fun initialize() {
        val element = UserInfoUiController().load()
        controller = element.getController()
        controller.init(user, false)
        container.children.add(element.component)
    }

    fun onModifyButtonClicked() {
        val userInfo = controller.userInfo
        if (userInfo != null) {
            user.updateInformation(userInfo)
            PromptDialogHelper.start("修改成功", "用户信息修改成功！")
                .addCloseButton("好", "CHECK", null)
                .createAndShow()
        }
    }

}