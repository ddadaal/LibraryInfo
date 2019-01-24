package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.vo.bookmanagement.BookInfoVo
import java.io.Serializable

interface BookManagementStrategy: Serializable {
    fun editBook(bookId: String, info: BookInfoVo)
}
