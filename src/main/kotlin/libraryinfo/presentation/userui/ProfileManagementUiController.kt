package libraryinfo.presentation.userui

import com.jfoenix.controls.JFXButton
import javafx.scene.layout.HBox
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.presentation.adminui.UserInfoUiController
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.user.UserRepository

class ProfileManagementUiController : UiController {

    lateinit var modifyButton: JFXButton
    lateinit var container: HBox
    lateinit var controller: UserInfoUiController
    var user = LoginAppServiceFactory.service.currentUser!!

    override fun load(): UiElement {
        return doLoad("/fxml/userui/ProfileManagement.fxml")
    }

    fun initialize() {
        val element = UserInfoUiController().load()
        controller = element.getController()
        controller.setUser(user)
        container.children.add(element.component)
    }

    fun onModifyButtonClicked() {
        val userInfo = controller.userInfo
        if (userInfo != null) {
            user.name = userInfo.name
            user.password = userInfo.password
            UserRepository.save()
        }
    }

}