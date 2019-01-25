package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class TeacherBorrowStrategy: SimpleBorrowStrategy(
        Duration.ofDays(30),
        arrayListOf("0", "1", "2")
)