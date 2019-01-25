package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.domain.exception.NoSuchBookException
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.bookmanagement.BookInfoVo
import java.util.*

class AdminBookManagementStrategy: BookManagementStrategy {
    override fun editBook(bookId: UUID, info: BookInfoVo) {
        val book = BookRepository.data.find { it.id == bookId } ?: throw NoSuchBookException()
        book.edit(info)
    }
}