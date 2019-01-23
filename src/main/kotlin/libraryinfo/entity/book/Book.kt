package libraryinfo.entity.book

import libraryinfo.entity.book.doc.Doc
import libraryinfo.repository.book.BookRepository
import java.io.Serializable
import java.time.LocalDateTime

class Book(): Serializable {
    lateinit var id: String
    lateinit var name: String
    lateinit var category: String
    lateinit var availableDocs: ArrayList<Doc>

    var borrowRecordId: String? = null

    constructor(id: String, name: String, category: String, availableDocs: ArrayList<Doc>): this() {
        this.id = id
        this.name = name
        this.category = category
        this.availableDocs = availableDocs
    }

    fun beBorrowed(recordId: String) {
        this.borrowRecordId = recordId
        BookRepository.save()
    }

    fun beReturned() {
        this.borrowRecordId = null
        BookRepository.save()
    }
}