package vds.developer.aceworkout.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Exercise", indices = [Index("exerciseId")])
data class ExerciseEntity(
        @PrimaryKey(autoGenerate = true)
        val exerciseId: Long,
        val name: String,
        val bodyPart: String,
        val isWeighted: Boolean
)
