package libraryinfo.repository.book

import libraryinfo.domain.entity.book.Book
import libraryinfo.repository.Repository

object BookRepository: Repository<ArrayList<Book>>("books", ArrayList())
