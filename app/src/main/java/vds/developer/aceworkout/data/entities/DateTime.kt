package vds.developer.aceworkout.data.entities

import java.time.LocalDate
import java.time.ZonedDateTime

data class DateTime(
        val localDate: LocalDate,
//    val localTime: LocalTime,
//        @TypeConverters(DateTime::class)
        val zoneDateTime: ZonedDateTime
//    val zoneId: ZoneId
)