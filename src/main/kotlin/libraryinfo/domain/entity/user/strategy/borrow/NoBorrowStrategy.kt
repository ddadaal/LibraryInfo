package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class NoBorrowStrategy : SimpleBorrowStrategy(
    Duration.ZERO,
    ArrayList()
)