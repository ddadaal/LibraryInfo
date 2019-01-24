package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class TeacherBorrowStrategy(
        override val duration: Duration = Duration.ofDays(30),
        override val availableCategories: ArrayList<String> = arrayListOf("0", "1", "2")
) : BorrowStrategy