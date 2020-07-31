package vds.developer.aceworkout.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

// all longs are in seconds
class DateTypeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromZonedDateTime(value: Long?): ZonedDateTime? {
        return if (value == null) null else ZonedDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneId.systemDefault())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun zonedDateTimeToLong(date: ZonedDateTime?): Long? {
        return date?.toEpochSecond() ?: 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToLong(date: LocalDate?): Long? {
        if (date == null) return 0
        return date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromDate(value: Long?): LocalDate? {
        return if (value == null) null else Instant.ofEpochSecond(value).atZone(ZoneId.systemDefault()).toLocalDate()
    }
}