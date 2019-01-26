package libraryinfo.domain.entity.user.userprivilege

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.bookmanagement.SimpleBookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.BorrowPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.SimpleBorrowPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.SimpleUserManagementPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementPrivilege
import libraryinfo.presentation.internal.UiElement
import libraryinfo.presentation.userui.UserUiController
import java.time.Duration

class SystemPrivilege: UserPrivilege {

    override var borrowPrivilege: BorrowPrivilege = SimpleBorrowPrivilege(
            Duration.ofHours(24*365),
            0,
            ArrayList()
        )

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
