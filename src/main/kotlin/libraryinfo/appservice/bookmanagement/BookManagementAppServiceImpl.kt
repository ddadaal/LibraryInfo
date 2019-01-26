package libraryinfo.appservice.bookmanagement

import libraryinfo.appservice.auth.checkPrivilege
import libraryinfo.domain.service.bookmanagement.BookManagementDomainService
import libraryinfo.util.Id
import libraryinfo.vo.bookmanagement.BookEditInfoVo

class BookManagementAppServiceImpl: BookManagementAppService {
    override fun editBook(bookId: Id, info: BookEditInfoVo) {
        checkPrivilege { it.bookManagementPrivilege.canEditBook }

        BookManagementDomainService.editBook(bookId, info)

    }

}