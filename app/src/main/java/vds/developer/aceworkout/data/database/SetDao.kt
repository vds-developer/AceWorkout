package vds.developer.aceworkout.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import vds.developer.aceworkout.data.entities.Set

@Dao
interface SetDao {
    @Query("SELECT * FROM `Set` ORDER BY setId")
    suspend fun getAllSet():List<Set>

    @Query("SELECT * FROM `Set` where trainingDayId = :trainingDayId ORDER BY setId")
    suspend fun getSetByTrainingDayId(trainingDayId: Long): List<Set>

    @Query("SELECT * FROM `Set` WHERE exerciseId = :exerciseId ORDER BY setId")
    suspend fun getSetByExerciseId(exerciseId : Long) : List<Set>
}