package libraryinfo.appservice.bookmanagement

import libraryinfo.util.Id
import libraryinfo.vo.bookmanagement.BookEditInfoVo

interface BookManagementAppService {
    fun editBook(bookId: Id, info: BookEditInfoVo)

    companion object {
        val SERVICE: BookManagementAppService = BookManagementAppServiceImpl()
    }
}