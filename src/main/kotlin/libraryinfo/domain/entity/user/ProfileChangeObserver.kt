package libraryinfo.domain.entity.user

import java.time.LocalDateTime

interface ProfileChangeObserver {
    fun onProfileChange(user: User, time: LocalDateTime)
}
