package libraryinfo.domain.book.doc

import libraryinfo.domain.book.reader.Reader

class PDF: Doc {
    override val reader: Reader
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val name: String
        get() = "PDF"

}
