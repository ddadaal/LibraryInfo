package libraryinfo.util

import java.io.Serializable
import java.util.*

class Id(): Serializable {

    var uuid: UUID

    init {
        uuid = UUID.randomUUID()
    }

    constructor(uuid: UUID): this() {
        this.uuid = uuid
    }

    constructor(str: String): this() {
        this.uuid = UUID.fromString(str)
    }

    fun substring(len: Int): String {
        return uuid.toString().substring(0, len)
    }

    override fun toString(): String {
        return uuid.toString()
    }

    val short: String
        get() = substring(6)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Id) return false

        if (uuid != other.uuid) return false

        return true
    }

    override fun hashCode(): Int {
        return uuid.hashCode()
    }


}
