package libraryinfo.repository.notification

import libraryinfo.domain.notification.Notification
import libraryinfo.repository.Repository

object NotificationRepository: Repository() {

    fun update(userId: String): List<Notification> {
        return ArrayList()
    }

}
