package vds.developer.aceworkout.data.entities

import androidx.room.*
import java.sql.Date
import java.sql.Time

import java.time.LocalDate

@Entity(tableName = "TrainingDay", indices = [Index("trainingDayId")])
public data class TrainingDay(
        @PrimaryKey(autoGenerate = true)
        val trainingDayId : Long,
        val numberOfSets: Int,
        @Embedded
        val dateTime: DateTime
) {


}