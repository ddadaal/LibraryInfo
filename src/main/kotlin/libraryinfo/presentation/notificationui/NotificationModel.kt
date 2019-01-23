package libraryinfo.presentation.notificationui

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import libraryinfo.domain.notification.Notification

class NotificationModel(
    var voObjectProperty: ObjectProperty<Notification>
) : RecursiveTreeObject<NotificationModel>() {

    constructor(vo: Notification): this(SimpleObjectProperty(vo))


    val notification: Notification
        get() = voObjectProperty.get()

    fun voObjectPropertyProperty(): ObjectProperty<Notification>? {
        return voObjectProperty
    }

    fun setVoObjectProperty(voObjectProperty: Notification) {
        this.voObjectProperty.set(voObjectProperty)
    }
}
