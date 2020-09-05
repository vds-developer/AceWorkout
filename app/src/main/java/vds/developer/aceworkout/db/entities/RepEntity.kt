package vds.developer.aceworkout.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Rep", indices = [Index("repId"), Index("setId"), Index("exerciseId")],
        foreignKeys = [ForeignKey(
                entity = SetEntity::class,
                parentColumns = ["setId"],
                childColumns = ["setId"],
                onDelete = ForeignKey.CASCADE),
            ForeignKey(
                    entity = ExerciseEntity::class,
                    parentColumns = ["exerciseId"],
                    childColumns = ["exerciseId"],
                    onDelete = ForeignKey.CASCADE)
        ])
data class RepEntity(
        @PrimaryKey(autoGenerate = true)
        val repId: Long,
        val setId: Long,
        val exerciseId: Long,
        val weight: Double,
        val reps: Int,
        val time: Long //(seconds)
)
