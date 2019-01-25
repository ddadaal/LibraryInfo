package libraryinfo.domain.entity.book

import libraryinfo.domain.entity.book.doc.Doc
import libraryinfo.domain.entity.book.instance.BookInstance
import libraryinfo.domain.exception.NoMoreInstanceException
import libraryinfo.repository.book.BookRepository
import libraryinfo.vo.bookmanagement.BookInfoVo
import java.io.Serializable
import java.util.*

class Book(): Serializable {
    lateinit var id: UUID
    lateinit var name: String
    lateinit var category: String
    lateinit var availableDocs: ArrayList<Doc>

    lateinit var instances: ArrayList<BookInstance>

    constructor(id: UUID, name: String, category: String, availableDocs: ArrayList<Doc>, instances: ArrayList<BookInstance>): this() {
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

    fun edit(info: BookInfoVo){
        this.category = info.category
        this.name = info.name
        this.availableDocs.clear()
        this.availableDocs.addAll(info.availableDocs)
    }

}