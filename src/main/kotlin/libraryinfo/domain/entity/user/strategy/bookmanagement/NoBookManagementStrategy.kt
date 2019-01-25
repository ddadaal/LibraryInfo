package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.domain.exception.PermissionDeniedException
import libraryinfo.vo.bookmanagement.BookInfoVo
import java.util.*

class NoBookManagementStrategy: BookManagementStrategy {
    override fun editBook(bookId: UUID, info: BookInfoVo) {
        throw PermissionDeniedException()
    }
}