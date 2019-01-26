package libraryinfo.presentation.adminui

import libraryinfo.appservice.usermanagement.UserManagementAppService
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper

class UserInfoEditingDialog(val user: User, val onClose: () -> Unit, title: String, content: String, val allowChangeType: Boolean) : PromptDialogHelper(title, content) {

    init {
        val element = UserInfoUiController().load()
        setContent(element.component)
        val controller = element.getController<UserInfoUiController>()
        controller.init(user, allowChangeType)
        addButton("确认修改", "") {
            val userInfo = controller.userInfo
            if (userInfo != null) {
                user.updateInformation(userInfo)
                Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
                onClose()
            }
        }
    }


}