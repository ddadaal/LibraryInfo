package libraryinfo.presentation.internal

import javafx.scene.Scene
import javafx.stage.Stage
import libraryinfo.presentation.mainui.ConcreteFrameworkUi
import libraryinfo.presentation.mainui.Framework
import java.time.LocalDateTime

object Globals {

    lateinit var frameworkUi: ConcreteFrameworkUi
    lateinit var stage: Stage
    lateinit var loginTime: LocalDateTime

    val framework: Framework
        get() = frameworkUi.framework

    fun closeStage() {
        stage.close()
    }

    fun changeScene(newScene: Scene) {
        stage.scene = newScene
        stage.sizeToScene()
        stage.centerOnScreen()
    }

}
