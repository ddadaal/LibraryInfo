package libraryinfo.domain.user

import libraryinfo.domain.user.usertype.UserType
import libraryinfo.presentation.internal.UiElement
import java.time.LocalDateTime

class User(
    var id: String,
    var name: String,
    var workSince: LocalDateTime,
    var password: String,
    var type: UserType
) {
    val mainUiElement: UiElement
        get() = type.mainUiElement
}
