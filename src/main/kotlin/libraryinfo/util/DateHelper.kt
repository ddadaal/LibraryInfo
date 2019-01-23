package libraryinfo.util

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateHelper {
    private const val DATE_PATTERN = "yyyy/MM/dd HH:mm:ss"
    private val dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN).withZone(ZoneId.systemDefault())

    /**
     * Get formalized date string according to the millisecond.
     * @param millisecond timestamp in millisecond
     * @return formalized date string
     */
    fun fromTimestamp(millisecond: Long): String {
        return dateFormatter.format(Instant.ofEpochMilli(millisecond))
    }

    /**
     * Formalize data according to the date.
     * @param date date
     * @return formalized date string
     */
    fun fromDate(date: LocalDateTime): String {
        return dateFormatter.format(date)
    }

    /**
     * Get formalized current date string with default DATE_PATTERN.
     * @return formalized current date string
     */
    fun currentDateString(): String {
        return fromTimestamp(System.currentTimeMillis())
    }

    /**
     * Get formalized current date string with custom dataPattern.
     * @return formalized current date string in custom date pattern
     */
    fun currentDateString(dataPattern: String): String {
        val formatter = DateTimeFormatter.ofPattern(dataPattern).withZone(ZoneId.systemDefault())
        return formatter.format(Instant.ofEpochMilli(System.currentTimeMillis()))
    }

    fun currentDateStringForLog(): String {
        return currentDateString("yyyy-MM-dd HH:mm:ss.SSS")
    }
}
