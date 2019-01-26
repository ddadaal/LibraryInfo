package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.domain.exception.NoSuchBookException
import libraryinfo.repository.book.BookRepository
import libraryinfo.util.Id
import libraryinfo.vo.bookmanagement.BookEditInfoVo

class SimpleBookManagementPrivilege(
    override var canEditBook: Boolean
) : BookManagementPrivilege