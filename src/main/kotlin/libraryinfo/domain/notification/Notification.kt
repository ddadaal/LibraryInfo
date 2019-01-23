package libraryinfo.domain.notification

import java.time.LocalDateTime


class Notification(
        val id: Int,
        val date: LocalDateTime,
        val senderId: String,
        val receiverIds: List<String>,
        val content: String
) {

}
