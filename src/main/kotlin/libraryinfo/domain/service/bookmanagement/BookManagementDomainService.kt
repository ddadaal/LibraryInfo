package libraryinfo.domain.service.bookmanagement

import libraryinfo.domain.exception.NoSuchBookException
import libraryinfo.repository.book.BookRepository
import libraryinfo.util.Id
import libraryinfo.vo.bookmanagement.BookEditInfoVo

object BookManagementDomainService {
    fun editBook(bookId: Id, info: BookEditInfoVo) {
        val book = BookRepository.data.find { it.id == bookId } ?: throw NoSuchBookException()

        book.edit(info)
    }
}