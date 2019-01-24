package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class GraduateBorrowStrategy(
        override val duration: Duration = Duration.ofDays(15),
        override val availableCategories: ArrayList<String> = arrayListOf("1", "2")
) : BorrowStrategy