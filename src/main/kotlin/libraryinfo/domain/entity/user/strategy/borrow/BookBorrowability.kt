package libraryinfo.domain.entity.user.strategy.borrow

class BookBorrowability(val error: String? = null) {
    val canBorrow: Boolean = error == null
}
