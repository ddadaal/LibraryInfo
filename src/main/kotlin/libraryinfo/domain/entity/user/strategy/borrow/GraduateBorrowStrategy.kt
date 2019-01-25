package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class GraduateBorrowStrategy : SimpleBorrowStrategy(
    Duration.ofDays(15),
    arrayListOf("1", "2")
)