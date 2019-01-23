package libraryinfo.presentation.mainui

import javafx.event.ActionEvent
import libraryinfo.presentation.internal.Globals
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.internal.UiLoader

class ManagerHomeUiController : BaseHomepageUiController() {

    private val uiController
        get() = this.frameworkUiController as ManagerUiController

    fun initialize() {
        super.refresh()
        textWelcome.text = "欢迎：总经理" + Globals.currentUser.name
    }

    override fun load(): UiElement {
        return UiLoader<ManagerHomeUiController>("/fxml/managerui/ManagerHome.fxml").loadAndGetElement()
    }

    fun onBtnAuditClicked(actionEvent: ActionEvent) {
        uiController.onAuditFunctionClicked(actionEvent)
    }

    fun onBtnComSalePromotionClicked(actionEvent: ActionEvent) {

    }

    fun onBtnClientPromotionClicked(actionEvent: ActionEvent) {

    }

    fun onBtnNotificationClicked(actionEvent: ActionEvent) {
        uiController.onNotificationFunctionButtonClicked(actionEvent)
    }
}
