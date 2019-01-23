package libraryinfo.presentation.mainui

import javafx.event.ActionEvent
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.internal.UiLoader

class ManagerUiController : FrameworkUiController() {
    override fun load(): UiElement {
        return UiLoader<ManagerUiController>("/fxml/mainui/ManagerUi.fxml").loadAndGetElement()
    }

    override fun initialize() {
        // 必须有。调用基类初始化来初始化基类的元素。
        super.initialize()
        // 如果还需要初始化自己的元素请接着写。
    }

    /**
     * 调用父类切换界面方法实现切换界面。
     * 第一个参数是功能Controller（需要实现ExternalLoadableUiController），第二个是标题文字。
     * @see .switchFunction
     */
    fun onAuditFunctionClicked(actionEvent: ActionEvent) {
        //        switchFunction(AuditUiController.class, "审批单据", true);
    }

    /*
    * 增加一个HomeUiController后，重写这个方法做到退回主界面。
    */
    override fun switchBackToHome() {
        switchFunction(ManagerHomeUiController::class.java, "主界面", true)
    }


}
