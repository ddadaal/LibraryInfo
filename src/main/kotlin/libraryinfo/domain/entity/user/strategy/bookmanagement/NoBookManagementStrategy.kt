package libraryinfo.domain.entity.user.strategy.bookmanagement

import libraryinfo.vo.bookmanagement.BookInfoVo

class NoBookManagementStrategy: BookManagementStrategy {
    override fun editBook(bookId: String, info: BookInfoVo) {
    }
}