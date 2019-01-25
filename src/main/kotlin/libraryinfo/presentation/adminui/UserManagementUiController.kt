package libraryinfo.presentation.adminui

import com.jfoenix.controls.JFXTreeTableView
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import libraryinfo.domain.entity.user.User
import libraryinfo.presentation.internal.UiController
import libraryinfo.presentation.internal.UiElement

class UserModel(val user:User):RecursiveTreeObject<UserModel>(){

}

class UserManagementUiController:UiController {


    override fun load(): UiElement {
        return doLoad("/fxml/adminui/UserManagement.fxml")
    }

    fun onAddButtonClicked(){

    }

    fun onModifyButtonClicked(){

    }

    fun onReportButtonClicked(){

    }

    fun onPaymentButtonClicked(){

    }
}