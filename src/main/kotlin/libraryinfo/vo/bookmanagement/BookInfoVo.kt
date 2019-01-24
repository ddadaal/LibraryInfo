package libraryinfo.vo.bookmanagement

import libraryinfo.domain.entity.book.doc.Doc

data class BookInfoVo(
    val name: String,
    val category: String,
    val availableDocs: List<Doc>
)