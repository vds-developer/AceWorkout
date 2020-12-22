package vds.developer.aceworkout.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import vds.developer.aceworkout.db.entities.SetEntity

@Dao
interface SetDao {
    @Query("SELECT * FROM `Set` ORDER BY setId")
    suspend fun getAllSet(): List<SetEntity>

    @Query("SELECT * FROM `Set` where trainingDayId = :trainingDayId ORDER BY setId")
    suspend fun getSetByTrainingDayIds(trainingDayId: Long): List<SetEntity>

    @Query("SELECT * FROM `Set` WHERE trainingDayId IN (:trainingDayIds) ORDER BY setId")
    suspend fun getSetByTrainingDayIds(trainingDayIds: List<Long>) : List<SetEntity>

    @Query("SELECT * FROM `Set` WHERE exerciseId = :exerciseId ORDER BY setId")
    suspend fun getSetByExerciseId(exerciseId: Long): List<SetEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addSet(setEntity: SetEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addSets(setEntity: List<SetEntity>)
}