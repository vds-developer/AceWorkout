package vds.developer.aceworkout.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Set", indices = [Index("trainingDayId"), Index("setId")],
        foreignKeys =
        [ForeignKey(
                entity = TrainingDayEntity::class,
                parentColumns = ["trainingDayId"],
                childColumns = ["trainingDayId"],
                onDelete = CASCADE)])
data class SetEntity(
        @PrimaryKey(autoGenerate = true)
        val setId: Long,
        // exercise name
        val setName: String,
        val trainingDayId: Long,
        val exerciseId: Long,
        val numberOfReps: Int
)
