package libraryinfo.domain.entity.user.userprivilege

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.bookmanagement.SimpleBookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.BorrowPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.TeacherBorrowPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.SimpleUserManagementPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementPrivilege

class TeacherPrivilege : UserPrivilege {


    override var borrowPrivilege: BorrowPrivilege = TeacherBorrowPrivilege()

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