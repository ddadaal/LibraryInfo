package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class TeacherBorrowPrivilege: SimpleBorrowPrivilege(
        Duration.ofDays(30),
        10,
        arrayListOf("0", "1", "2")
)