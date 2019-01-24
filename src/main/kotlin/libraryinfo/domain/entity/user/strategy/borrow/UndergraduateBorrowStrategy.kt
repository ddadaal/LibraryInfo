package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class UndergraduateBorrowStrategy(
        override val duration: Duration = Duration.ofDays(15),
        override val availableCategories: ArrayList<String> = arrayListOf("0","1")
) : BorrowStrategy