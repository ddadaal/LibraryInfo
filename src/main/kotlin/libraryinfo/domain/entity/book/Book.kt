package libraryinfo.domain.entity.book

import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.exception.NoMoreInstanceException
import libraryinfo.repository.book.BookRepository
import java.io.Serializable

class Book(): Serializable {
    lateinit var id: String
    lateinit var name: String
    lateinit var category: String
    lateinit var availableDocs: ArrayList<Doc>

    lateinit var instances: ArrayList<BookInstance>

    constructor(id: String, name: String, category: String, availableDocs: ArrayList<Doc>, instances: ArrayList<BookInstance>): this() {
        this.id = id
        this.name = name
        this.category = category
        this.availableDocs = availableDocs
        this.instances = instances
    }

    fun borrow(instance: BookInstance) {

        this.instances.remove(instance)
        BookRepository.save()
    }

    fun `return`(instance: BookInstance) {
        this.instances.add(instance)
        BookRepository.save()
    }

}