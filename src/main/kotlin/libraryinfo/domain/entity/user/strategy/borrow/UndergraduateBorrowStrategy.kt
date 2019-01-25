package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class UndergraduateBorrowStrategy : SimpleBorrowStrategy(
    Duration.ofDays(15),
    arrayListOf("0", "1")
)