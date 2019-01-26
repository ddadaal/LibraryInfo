package libraryinfo.presentation.userui

import com.jfoenix.controls.JFXTreeTableColumn
import com.jfoenix.controls.JFXTreeTableView
import com.jfoenix.controls.RecursiveTreeItem
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.SelectionMode
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.domain.entity.book.Book
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.repository.book.BookRepository
import libraryinfo.util.DateHelper
import libraryinfo.vo.borrowrecord.BorrowRecordVo
import java.time.LocalDateTime


class MyBookUiController : UiController {
    lateinit var container: HBox
    lateinit var tableController: BorrowRecordTableUiController

    fun initialize() {
        val element = BorrowRecordTableUiController().load()
        container.children.add(element.component)
        tableController = element.getController()
    }

    val user: User
        get() = LoginAppServiceFactory.service.currentUser!!


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