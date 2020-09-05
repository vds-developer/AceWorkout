package vds.developer.aceworkout.db.entities

import java.time.LocalDate
import java.time.ZonedDateTime

data class DateTimeEntity(
        val localDate: LocalDate,
//    val localTime: LocalTime,
//        @TypeConverters(DateTime::class)
        val zoneDateTime: ZonedDateTime
//    val zoneId: ZoneId
)