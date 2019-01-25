package libraryinfo.presentation.adminui

import libraryinfo.presentation.mainui.Framework
import libraryinfo.presentation.mainui.MainUi

class AdminUiController:MainUi("/fxml/adminui/AdminUi.fxml"){
    override lateinit var  framework: Framework

    fun switchBackToHome(){
        framework.switchFunction(AdminHomeUiController().load(),"主页")
    }

    fun onUserManagementClicked(){
        framework.switchFunction(UserManagementUiController().load(),"用户管理")
    }

    fun onBookManagementClicked(){
        framework.switchFunction(BookManagementUiController().load(),"图书管理")
    }

}