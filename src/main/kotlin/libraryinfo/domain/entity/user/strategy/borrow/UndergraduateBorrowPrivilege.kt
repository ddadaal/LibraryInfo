package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class UndergraduateBorrowPrivilege : SimpleBorrowPrivilege(
    Duration.ofDays(-1),
    1,
    arrayListOf("0", "1")
)