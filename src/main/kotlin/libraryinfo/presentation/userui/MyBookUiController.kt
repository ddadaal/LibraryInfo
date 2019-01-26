package libraryinfo.presentation.userui

import javafx.event.ActionEvent
import javafx.scene.layout.HBox
import libraryinfo.appservice.auth.AuthAppService
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement


class MyBookUiController : UiController {
    lateinit var container: HBox
    lateinit var tableController: BorrowRecordTableUiController

    fun initialize() {
        val element = BorrowRecordTableUiController().load()
        container.children.add(element.component)
        tableController = element.getController()
    }

    val user: User
        get() = AuthAppService.SERVICE.currentUser!!


    override fun load(): UiElement {
        return doLoad("/fxml/userui/MyBook.fxml")
    }


    fun onBtnRefreshClicked(actionEvent: ActionEvent) {
        tableController.updateItems()
    }

    fun onBtnReturnClicked(actionEvent: ActionEvent) {
        val instance = tableController.selected?.instance
        if (instance != null) {
            user.returnBook(instance)
            PromptDialogHelper.start("还书成功", "您已经归还了${instance.book.name}！")
                    .addCloseButton("好", "CHECK", null)
                    .createAndShow()
            tableController.updateItems()
        }
    }

}