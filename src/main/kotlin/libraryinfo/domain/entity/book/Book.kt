package libraryinfo.domain.entity.book

import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.repository.book.BookRepository
import libraryinfo.util.Id
import libraryinfo.vo.bookmanagement.BookEditInfoVo
import java.io.Serializable
import java.util.*

class Book(): Serializable {
    lateinit var id: Id
    lateinit var name: String
    lateinit var category: String
    lateinit var availableDocs: ArrayList<Doc>

    lateinit var instances: ArrayList<BookInstance>

    constructor(id: Id, name: String, category: String, availableDocs: ArrayList<Doc>, instances: ArrayList<BookInstance>): this() {
        this.id = id
        this.name = name
        this.category = category
        this.availableDocs = availableDocs
        this.instances = instances
    }

    fun borrow(instance: BookInstance) {
        BookRepository.save()
    }

    fun `return`(instance: BookInstance) {
        BookRepository.save()
    }

    fun edit(info: BookEditInfoVo){
        this.category = info.category
        this.name = info.name
        this.availableDocs.clear()
        this.availableDocs.addAll(info.availableDocs)
        BookRepository.save()
    }

}