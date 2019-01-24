package libraryinfo.presentation.internal

import javafx.scene.Scene
import javafx.stage.Stage
import libraryinfo.presentation.mainui.FrameworkUiController
import java.time.LocalDateTime

object Globals {

    lateinit var frameworkUiController: FrameworkUiController
    lateinit var stage: Stage
    lateinit var loginTime: LocalDateTime

    fun closeStage() {
        stage.close()
    }

    fun changeScene(newScene: Scene) {
        stage.scene = newScene
        stage.sizeToScene()
        stage.centerOnScreen()
    }

}
