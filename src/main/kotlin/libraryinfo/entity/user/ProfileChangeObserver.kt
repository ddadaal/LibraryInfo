package libraryinfo.entity.user

import libraryinfo.entity.user.User
import java.time.LocalDateTime

interface ProfileChangeObserver {
    fun onProfileChange(user: User, time: LocalDateTime)
}
