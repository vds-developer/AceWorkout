package vds.developer.aceworkout.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import vds.developer.aceworkout.data.entities.TrainingDay
import java.time.LocalDate

@Dao
interface TrainingDayDao{
    @Query("SELECT * FROM TrainingDay")
    fun getAllTrainingDay() : List<TrainingDay>

    @Query("SELECT * FROM TrainingDay WHERE localDate == :date limit 1" )
    suspend fun getTrainingDayByDate(date : LocalDate) : TrainingDay

    @Insert(onConflict = ABORT)
    suspend fun insertTrainingDay(trainingDay: TrainingDay)

    @Update
    suspend fun updateTrainingDay(trainingDay: TrainingDay)
}