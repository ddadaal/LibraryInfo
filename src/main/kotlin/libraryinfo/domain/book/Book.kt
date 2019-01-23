package libraryinfo.domain.book

import libraryinfo.domain.book.doc.Doc

class Book {
    lateinit var name: String
    lateinit var category: String
    var availableDocs: List<Doc> = ArrayList()
}