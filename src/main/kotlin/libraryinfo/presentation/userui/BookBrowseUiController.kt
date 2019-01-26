package libraryinfo.presentation.userui

import javafx.event.ActionEvent
import javafx.scene.layout.HBox
import libraryinfo.appservice.auth.AuthAppService
import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.util.DateHelper


class BookBrowseUiController : UiController {

    lateinit var container: HBox
    lateinit var tableController: BookTableUiController

    override fun load(): UiElement {
        return doLoad("/fxml/userui/BookBrowse.fxml")
    }

    fun initialize() {
        val tableElement = BookTableUiController().load()
        container.children.add(tableElement.component)
        tableController = tableElement.getController()
    }


    fun onBtnBorrowClicked(actionEvent: ActionEvent) {
        val book = tableController.selected
        if (book != null) {
            if (book.instances.filter { it.recordId == null }.isEmpty()) {
                PromptDialogHelper.start("没有剩余的书", "书已经被全部借完了！下次要快一点哦！")
                        .addCloseButton("好", "CHECK", null)
                        .createAndShow()
            } else {
                val instance = book.instances.filter { it.recordId == null }[0]
                val user = AuthAppService.currentUser!!
                try {
                    val recordId = user.borrowBook(instance)

                    val record = user.borrowRecords.find { it.id == recordId }!!

                    PromptDialogHelper.start("借书成功", "恭喜，您已经借了一本${book.name}。请在${DateHelper.fromDate(record.borrowTime + user.privilege.borrowPrivilege.maxDuration)}之前归还这本书，否则将会收取费用。")
                            .addCloseButton("好", "CHECK", null)
                            .createAndShow()

                    tableController.updateItems()
                } catch (e: PermissionDeniedException) {
                    PromptDialogHelper.start("错误", "您不能借这本书。原因：${e.message}")
                            .addCloseButton("好", "CHECK", null)
                            .createAndShow()
                }
            }

        }
    }

    fun onBtnReadClicked(actionEvent: ActionEvent) {
        val selected = tableController.selected
        if (selected != null) {
            PromptDialogHelper.start("", "")
                    .setContent(DocSelectionUiController().show(selected.availableDocs).component)
                    .createAndShow()
        }
    }

    fun onBtnRefreshClicked(actionEvent: ActionEvent) {
        tableController.updateItems()
    }

}