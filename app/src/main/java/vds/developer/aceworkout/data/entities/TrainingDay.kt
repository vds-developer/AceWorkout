package vds.developer.aceworkout.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "TrainingDay", indices = [Index("trainingDayId")])
data class TrainingDay(
        @PrimaryKey(autoGenerate = true)
        val trainingDayId: Long,

        val numberOfSets: Int,

        @Embedded
        val dateTime: DateTime
)