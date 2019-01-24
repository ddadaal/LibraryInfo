package libraryinfo.appservice.login

class LoginAppServiceFactory {
    companion object {
        val service: LoginAppService = LoginAppServiceImpl()
    }
}