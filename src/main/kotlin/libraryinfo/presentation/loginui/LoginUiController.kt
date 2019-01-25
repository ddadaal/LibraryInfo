package libraryinfo.presentation.loginui

import com.jfoenix.controls.*
import com.jfoenix.validation.RequiredFieldValidator
import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import javafx.stage.StageStyle
import libraryinfo.presentation.helpui.*
import libraryinfo.presentation.internal.*
import libraryinfo.appservice.login.LoginAppServiceFactory
import libraryinfo.domain.entity.notification.Notification
import libraryinfo.domain.service.systeminit.SystemInitDomainService
import libraryinfo.presentation.mainui.MainUi
import libraryinfo.repository.user.UserRepository
import java.time.LocalDateTime
import java.util.*

class LoginUiController : UiController {
    override fun load(): UiElement {
        return doLoad("/fxml/loginui/LoginUi.fxml")
    }

    lateinit var passwordField: JFXPasswordField
    lateinit var usernameField: JFXTextField
    lateinit var cancelButton: JFXButton
    lateinit var dialogContainer: StackPane
    lateinit var rootPane: BorderPane
    lateinit var loginButton: JFXButton

    val loginAppService = LoginAppServiceFactory.service

    fun init(stage: Stage) {
        Globals.stage = stage
        makeDraggable(stage, rootPane)
        makeResizeable(stage)

        SystemInitDomainService.init()
    }

    fun initialize() {
        val usernameValidator = RequiredFieldValidator()
        usernameValidator.message = "请输入用户名"
        usernameField.validators.add(usernameValidator)

        val passwordValidator = RequiredFieldValidator()
        passwordValidator.message = "请输入密码"
        passwordField.validators.add(passwordValidator)

        usernameField.focusedProperty().addListener { observable, oldValue, newValue ->
            if (!newValue) {
                usernameField.validate()
            }
        }

        passwordField.focusedProperty().addListener { observable, oldValue, newValue ->
            if (!newValue) {
                passwordField.validate()
            }
        }

        passwordField.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                loginButton.fire()
            }
        }


    }

    private fun validate(): Boolean {
        return usernameField.validate() and passwordField.validate()
    }

    fun onLoginButtonClicked() {
        if (!validate()) {
            return
        }

        loginButton.isDisable = true
        loginButton.text = "登录中"

        val thread = Thread {
            Platform.runLater {

                val username = usernameField.text

                if (loginAppService.login(username, passwordField.text)) {


                    val user = loginAppService.currentUser!!

                    user.notifications.add(Notification(LocalDateTime.now(), SystemInitDomainService.SYS_USER.id, "您已成功登录。"))
                    UserRepository.save()

                    Globals.closeStage()

                    // login successful
                    // init the globals

                    Globals.loginTime = LocalDateTime.now()

                    val newStage = Stage()

                    val ui = user.mainUiElement

                    val scene = Scene(ui.component)
                    newStage.scene = scene
                    newStage.height = 900.0
                    newStage.width = 1500.0
                    newStage.initStyle(StageStyle.UNDECORATED)

                    // init globals
                    Globals.frameworkUi = ui.getController<MainUi>()
                    Globals.framework.setStage(newStage)



                    newStage.show()
                } else {
                    loginButton.isDisable = false
                    loginButton.text = "登录"
                    val layout = JFXDialogLayout()
                    val button = JFXButton("好", MaterialIconView(MaterialIcon.CHECK))
                    layout.setBody(Label("登录失败！请检查用户名！或者寻找支持人员！"))
                    layout.setHeading(Label("登录失败！"))
                    layout.setActions(button)
                    val dialog = JFXDialog(dialogContainer, layout, JFXDialog.DialogTransition.CENTER)
                    button.setOnAction { dialog.close() }
                    dialog.show()
                }
            }
        }

        thread.start()
    }

    fun onBtnCancelClicked(actionEvent: ActionEvent) {
        Globals.closeStage()
    }

}
