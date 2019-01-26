package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class GraduateBorrowPrivilege : SimpleBorrowPrivilege(
    Duration.ofDays(15),
    2,
    arrayListOf("1", "2")
)