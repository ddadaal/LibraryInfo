package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.util.Id
import libraryinfo.vo.bookmanagement.BookEditInfoVo
import java.io.Serializable

interface BookManagementPrivilege: Serializable {
    var canEditBook: Boolean
}
