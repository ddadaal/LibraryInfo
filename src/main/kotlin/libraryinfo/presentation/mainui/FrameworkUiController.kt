package libraryinfo.presentation.mainui

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSpinner
import com.jfoenix.effects.JFXDepthManager
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.util.Duration
import libraryinfo.presentation.helpui.*
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.notificationui.NotificationUiController
import libraryinfo.appservice.login.LoginAppService
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.util.DateHelper

const val DEPTH = 3

abstract class FrameworkUiController : UiController {
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
    lateinit var btnDraft: JFXButton
    var subController: UiController? = null

    private val loginAppService = LoginAppServiceFactory.service

    val dialogStack = DialogStack()

    lateinit var notificationUiElement: UiElement

    fun refreshNotificationStatus(): Int {
        val controller = notificationUiElement.getController<NotificationUiController>()
        val count = controller.updateItems()
        btnNotification.text = String.format("通知（%d）", count)
        return count
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

    open fun initialize() {


        val timeline = Timeline(
            KeyFrame(Duration.millis(1000.0),
                EventHandler { event -> timeText.text = DateHelper.fromTimestamp(System.currentTimeMillis()) }
            )
        )
        timeline.cycleCount = Animation.INDEFINITE
        timeline.play()
        switchBackToHome()

        promptLabel.text = "欢迎你！" + loginAppService.currentUser?.name

        notificationUiElement = NotificationUiController().load()
    }


    fun setContent(content: Node) {
        contentPane.children.clear()
        contentPane.children.add(content)
    }

    fun onNotificationFunctionButtonClicked(actionEvent: ActionEvent) {
        refreshNotificationStatus()
        switchFunction(notificationUiElement, "通知", true)

    }


    fun showLoadingAnimation() {
        val spinner = JFXSpinner()
        spinner.style = "-jfx-radius: 70px"

        this.contentPane.children.clear()
        this.contentPane.children.add(spinner)
    }

    /**
     * 切换功能界面的方法。这个重载方法每次都会重新加载一个新的功能界面，以前的功能界面的状态将会被丢弃。
     *
     * @param clazz   对应功能界面的类对象
     * @param title   标题名称
     * @param refresh 如果新的UI界面和原来的界面是同一个界面的话，是否需要刷新。
     */

    fun switchFunction(clazz: Class<out UiController>, title: String, refresh: Boolean) {


        val thread = Thread {
            Platform.runLater { this.showLoadingAnimation() }
            Thread.sleep(200)

            Platform.runLater {
                switchFunction(clazz.newInstance().load(), title, refresh)
            }
        }
        thread.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        thread.start()


    }

    /**
     * 另一种通过package切换界面的方法。这个方法允许用户自己维护原来的功能界面的状态。
     *
     * @param uiElementPackage package
     * @param refresh   如果新的UI界面和原来的界面是同一个界面的话（通过controller是否一致来判断），是否需要刷新。
     * @see .switchFunction
     */

    fun switchFunction(uiElementPackage: UiElement, title: String, refresh: Boolean) {
        switchFunction(uiElementPackage.component, uiElementPackage.getController(), title, refresh)
    }

    /**
     * 另一种通过package切换界面的方法。这个方法允许用户自己维护原来的功能界面的状态。
     *
     * @param parent     功能对象UI元素
     * @param controller 控制器
     * @see .switchFunction
     */
    fun switchFunction(parent: Parent, controller: UiController, title: String, refresh: Boolean) {

        subController?.onClose()

        if (refresh || subController == null) {
            subController = controller
            this.contentPane.children.clear()
            this.contentPane.children.add(parent)
            this.titleText.text = title
        }
    }

    /**
     * 增加一个HomeUiController后，重写这个方法做到退回主界面。
     */
    open fun switchBackToHome() {

    }

    fun onBtnAboutClicked(actionEvent: ActionEvent) {
        switchFunction(AboutPageController::class.java, "关于", true)
    }
}
