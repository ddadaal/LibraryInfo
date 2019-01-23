package libraryinfo

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle
import libraryinfo.presentation.loginui.LoginUiController

class Client : Application() {

    /**
     * The main entry point for all JavaFcaX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     *
     *
     *
     * NOTE: This method is called on the JavaFX Application Thread.
     *
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    override fun start(primaryStage: Stage) {
        primaryStage.initStyle(StageStyle.UNDECORATED)

        val loader = FXMLLoader()
        loader.location = javaClass.getResource("/fxml/loginui/LoginUi.fxml")
        val newScene = Scene(loader.load<Parent>())


        val controller = loader.getController<LoginUiController>()


        primaryStage.scene = newScene
        controller.init(primaryStage)
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Client::class.java, *args)
}