package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.vo.bookmanagement.BookInfoVo
import java.io.Serializable
import java.util.*

interface BookManagementStrategy: Serializable {
    fun editBook(bookId: UUID, info: BookInfoVo)
}
