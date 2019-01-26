package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class NoBorrowPrivilege : SimpleBorrowPrivilege(
    Duration.ZERO,
    0,
    ArrayList()
)