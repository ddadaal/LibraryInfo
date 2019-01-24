package libraryinfo.appservice.usermanagement

class UserManagementAppServiceFactory {
    companion object {
        val service: UserManagementAppService = UserManagementAppServiceImpl()
    }
}