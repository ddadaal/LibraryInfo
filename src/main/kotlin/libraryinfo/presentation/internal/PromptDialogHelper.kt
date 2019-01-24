package libraryinfo.presentation.internal

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXDialogLayout
import com.jfoenix.controls.JFXTreeTableView
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.StackPane
import javafx.scene.text.Font
import javafx.scene.text.Text

import java.util.ArrayList
import java.util.Collections


class PromptDialogHelper(title: String, content: String) {
    private var titleText: Text? = null
    var contentNode: Node? = null
        private set
    private val buttonList = ArrayList<JFXButton>()
    private val dialog = JFXDialog(null, null, JFXDialog.DialogTransition.CENTER)

    init {
        val titleText = Text(title)
        titleText.font = Font.font(20.0)
        this.titleText = titleText
        this.contentNode = Text(content)
    }

    fun addTable(table: JFXTreeTableView<*>): PromptDialogHelper {
        val pane = AnchorPane()
        pane.children.add(table)
        AnchorPane.setTopAnchor(table, 24.0)
        AnchorPane.setBottomAnchor(table, 24.0)
        AnchorPane.setLeftAnchor(table, 24.0)
        AnchorPane.setRightAnchor(table, 24.0)
        contentNode = table
        return this
    }

    fun getTitleText(): Text? {
        return titleText
    }

    fun setTitleText(titleText: Text): PromptDialogHelper {
        this.titleText = titleText
        return this
    }

    fun setContentText(contentText: String): PromptDialogHelper {
        this.contentNode = Text(contentText)
        return this
    }

    fun addCloseButton(content: String, icon: Node, e: EventHandler<in MouseEvent>?): PromptDialogHelper {
        val button = JFXButton(content, icon)
        button.setOnMouseClicked { event ->
            Globals.framework.dialogStack.closeCurrentAndPopAndShowNext()
            e?.handle(event)
        }
        buttonList.add(button)
        return this
    }

    fun addCloseButton(content: String, glyphName: String, e: EventHandler<in MouseEvent>?): PromptDialogHelper {
        val icon = MaterialIconView()
        icon.glyphName = glyphName
        icon.glyphSize = 24
        return addCloseButton(content, icon, e)
    }

    fun addButton(content: String, icon: Node, e: (MouseEvent) -> Unit): PromptDialogHelper {
        val button = JFXButton(content, icon)
        button.onMouseClicked = EventHandler(e)
        buttonList.add(button)
        return this
    }

    fun setContent(node: Node): PromptDialogHelper {
        contentNode = node
        return this
    }

    fun addButton(content: String, glyphName: String, e: (MouseEvent) -> Unit): PromptDialogHelper {
        val icon = MaterialIconView()
        icon.glyphName = glyphName
        icon.glyphSize = 24
        return addButton(content, icon, e)
    }

    fun addButtons(vararg buttons: JFXButton): PromptDialogHelper {
        Collections.addAll(buttonList, *buttons)
        return this
    }

    fun create(container: StackPane = Globals.framework.dialogContainer): JFXDialog {
        val content = JFXDialogLayout()
        content.setHeading(titleText)
        content.setBody(contentNode)
        content.setActions(buttonList)
        dialog.content = content
        dialog.dialogContainer = container
        return dialog
    }

    fun createAndShow() {
        Globals.framework.dialogStack.pushAndShow(create())
    }

    companion object {

        fun start(title: String, content: String): PromptDialogHelper {
            return PromptDialogHelper(title, content)
        }
    }
}
