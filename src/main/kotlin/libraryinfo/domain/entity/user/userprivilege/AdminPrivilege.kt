package libraryinfo.domain.entity.user.userprivilege

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.bookmanagement.SimpleBookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.BorrowPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.NoBorrowPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.SimpleUserManagementPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementPrivilege
import libraryinfo.presentation.adminui.AdminUiController
import libraryinfo.presentation.internal.UiElement

class AdminPrivilege : UserPrivilege {

    override var borrowPrivilege: BorrowPrivilege = NoBorrowPrivilege()

    override var userManagementPrivilege: UserManagementPrivilege = SimpleUserManagementPrivilege(
        true,
        true,
        true,
        true
    )

    override var bookManagementPrivilege: BookManagementPrivilege = SimpleBookManagementPrivilege(
        true
    )


}