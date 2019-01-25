package libraryinfo.presentation.helpui

import com.jfoenix.controls.JFXTextField
import javafx.event.ActionEvent
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.PromptDialogHelper
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class ModificationSampleController: UiController {

    lateinit var tfName: JFXTextField
    var onClose: (() -> Unit)? = null

    override fun load(): UiElement {
        return doLoad("/fxml/helpui/ModificationSample.fxml")
    }

    fun show(info: String, onComplete: () -> Unit) {

        val ui = load()

        // 初始化ui的信息，不是this的！
        val controller = ui.getController<ModificationSampleController>()
        controller.tfName.text = info

        // 设置回调
        controller.onClose = onComplete
        // 使用helper显示

        PromptDialogHelper.start("", "")
            .setContent(ui.component)
            .createAndShow()
    }

    fun close() {
        onClose?.invoke()
    }

    fun onBtnSubmitClicked(actionEvent: ActionEvent) {
        close()
    }

    fun onBtnCancelClicked(actionEvent: ActionEvent) {
close()
    }

}
