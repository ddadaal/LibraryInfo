package libraryinfo.domain.entity.user.userprivilege

import libraryinfo.domain.entity.user.strategy.bookmanagement.BookManagementPrivilege
import libraryinfo.domain.entity.user.strategy.borrow.BorrowPrivilege
import libraryinfo.domain.entity.user.strategy.usermanagement.UserManagementPrivilege
import java.io.Serializable

interface UserPrivilege: Serializable {

    var borrowPrivilege: BorrowPrivilege

    var userManagementPrivilege: UserManagementPrivilege

    var bookManagementPrivilege: BookManagementPrivilege

}
