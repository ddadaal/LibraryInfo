package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.domain.exception.NoSuchBookException
import libraryinfo.repository.book.BookRepository
import libraryinfo.repository.user.UserRepository
import libraryinfo.vo.bookmanagement.BookInfoVo

class AdminBookManagementStrategy: BookManagementStrategy {
    override fun editBook(bookId: String, info: BookInfoVo) {
        val book = BookRepository.data.filter { it.id == bookId }.getOrNull(0) ?: throw NoSuchBookException()
        book.edit(info)
    }
}