package libraryinfo.presentation.userui

import com.jfoenix.controls.JFXComboBox
import javafx.event.ActionEvent
import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.reader.ReaderFactory

class DocItem(var doc: Doc) {
    override fun toString(): String {
        return doc.name
    }
}

class DocSelectionUiController: UiController {

    lateinit var cbDoc: JFXComboBox<DocItem>

    override fun load(): UiElement {
        return doLoad("/fxml/userui/DocSelection.fxml")
    }

    fun show(docs: List<Doc>): UiElement {
        val ui = load()
        val controller = ui.getController<DocSelectionUiController>()
        controller.cbDoc.items.addAll(docs.map { DocItem(it) })
        return ui
    }

    fun onBtnConfirmClicked(actionEvent: ActionEvent) {
        val selected = cbDoc.selectionModel.selectedItem
        if (selected != null) {
            PromptDialogHelper.start("", "")
                .setContent(ReaderFactory.getReader(selected.doc).display().component)
                .createAndShow()
        }
    }


}
