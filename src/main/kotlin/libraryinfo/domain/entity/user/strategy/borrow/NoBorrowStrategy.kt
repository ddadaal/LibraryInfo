package libraryinfo.domain.entity.user.strategy.borrow

import java.time.Duration

class NoBorrowStrategy(
        override val duration: Duration = Duration.ZERO,
        override val availableCategories: ArrayList<String> = ArrayList()
) : BorrowStrategy