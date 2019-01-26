package libraryinfo.domain.entity.user.userprivilege

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.bookmanagement.SimpleBookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.BorrowPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.GraduateBorrowPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.SimpleUserManagementPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementPrivilege

class GraduatePrivilege : UserPrivilege {

    override var borrowPrivilege: BorrowPrivilege = GraduateBorrowPrivilege()

    override var userManagementPrivilege: UserManagementPrivilege = SimpleUserManagementPrivilege(
        false,
        false,
        false,
        false
    )

    override var bookManagementPrivilege: BookManagementPrivilege = SimpleBookManagementPrivilege(
        false
    )

}
