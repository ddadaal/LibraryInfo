package libraryinfo.presentation.mainui

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSpinner
import com.jfoenix.effects.JFXDepthManager
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.beans.DefaultProperty
import javafx.beans.NamedArg
import javafx.collections.ListChangeListener
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.util.Duration
import libraryinfo.presentation.helpui.*
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.notificationui.NotificationUiController
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.presentation.internal.UiElement
import libraryinfo.util.DateHelper

//@DefaultProperty("children")
class Framework(
    @NamedArg("functionButtons") var functionButtons: List<Button>
): AnchorPane() {


    private val DEPTH = 3

    lateinit var dialogContainer: StackPane
    lateinit var drawer: GridPane
    lateinit var titleBar: GridPane
    lateinit var bottomBar: GridPane
    lateinit var contentPane: Pane
    lateinit var closeButton: JFXButton
    lateinit var timeText: Text
    lateinit var maximizeButton: JFXButton
    lateinit var minimizeButton: JFXButton
    lateinit var maximizeButtonGlyph: MaterialIconView
    lateinit var promptLabel: Label
    lateinit var titleText: Text
    lateinit var btnNotification: JFXButton
    var subController: UiController? = null
    lateinit var vboxFunctionButtons: VBox
    var onSwitchBackToHome: EventHandler<ActionEvent>? = null
    // function buttons

    private val loginAppService = LoginAppServiceFactory.service

    init {
        val loader = FXMLLoader(javaClass.getResource("/fxml/helpui/Framework.fxml"))
        loader.setRoot(this)
        loader.setController(this)

        loader.load<Framework>()

    }


    val dialogStack = DialogStack()

    lateinit var notificationUi: UiElement

    val notificationUiController
        get() = notificationUi.getController<NotificationUiController>()

    fun refreshNotificationStatus() {
        notificationUiController.updateItems()
    }

    fun setStage(stage: Stage) {
        makeResizeable(stage)
        makeDraggable(stage, titleBar)

        JFXDepthManager.setDepth(drawer, DEPTH)
        JFXDepthManager.setDepth(titleBar, DEPTH)
        JFXDepthManager.setDepth(bottomBar, DEPTH)
        JFXDepthManager.setDepth(contentPane, DEPTH)
        closeButton.setOnMouseClicked { e -> stage.close() }
        minimizeButton.setOnMouseClicked { e -> stage.isIconified = true }
        maximizeButton.setOnMouseClicked { e ->
            if (stage.isMaximized) {
                stage.isMaximized = false
                maximizeButtonGlyph.glyphName = "KEYBOARD_ARROW_UP"
            } else {
                stage.isMaximized = true
                maximizeButtonGlyph.glyphName = "KEYBOARD_ARROW_DOWN"
            }

        }

        Globals.stage = stage
    }

    @FXML
    private fun initialize() {


        val timeline = Timeline(
            KeyFrame(Duration.millis(1000.0),
                EventHandler { timeText.text = DateHelper.fromTimestamp(System.currentTimeMillis()) }
            )
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
        switchBackToHome(null)

        promptLabel.text = "欢迎你！" + loginAppService.currentUser?.name

        notificationUi = NotificationUiController().load()

        notificationUiController.notificationModels.addListener( ListChangeListener {
            btnNotification.text = "通知（${notificationUiController.notificationModels.size}）"
        })

        refreshNotificationStatus()

        vboxFunctionButtons.children.addAll(functionButtons)
    }


    fun setContent(content: Node) {
        contentPane.children.clear()
        contentPane.children.add(content)
    }

    fun onNotificationFunctionButtonClicked(actionEvent: ActionEvent) {
        refreshNotificationStatus()
        switchFunction(notificationUi, "通知")

    }


    fun showLoadingAnimation() {
        val spinner = JFXSpinner()
        spinner.style = "-jfx-radius: 70px"

        this.contentPane.children.clear()
        this.contentPane.children.add(spinner)
    }

    fun switchFunction(uiElement: UiElement, title: String) {
        val thread = Thread {
            Platform.runLater { this.showLoadingAnimation() }
            Thread.sleep(200)

            Platform.runLater {
                subController?.onClose()

                if (subController == null) {
                    subController = uiElement.getController()

                }

                this.contentPane.children.clear()
                this.contentPane.children.add(uiElement.component)
                this.titleText.text = title
            }

        }
        thread.uncaughtExceptionHandler = Thread.UncaughtExceptionHandler { _, throwable -> throwable.printStackTrace()}
        thread.start()
    }

    fun switchBackToHome(actionEvent: ActionEvent?) {
        onSwitchBackToHome?.handle(actionEvent)
    }

    fun onBtnAboutClicked(actionEvent: ActionEvent) {
        switchFunction(AboutPageController().load(), "关于")
    }
}
