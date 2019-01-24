package libraryinfo.presentation.mainui

import javafx.event.ActionEvent
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.internal.UiLoader
import libraryinfo.appservice.login.LoginAppService
import libraryinfo.appservice.login.LoginAppServiceFactory

class ManagerHomeUiController : BaseHomepageUiController() {

    private val loginAppService = LoginAppServiceFactory.service

    private val uiController
        get() = this.frameworkUiController as ManagerUiController

    fun initialize() {
        super.refresh()
        textWelcome.text = "欢迎：总经理" + loginAppService.currentUser?.name
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
