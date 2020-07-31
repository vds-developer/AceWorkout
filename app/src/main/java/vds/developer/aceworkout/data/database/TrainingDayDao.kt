package vds.developer.aceworkout.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import androidx.room.Update
import vds.developer.aceworkout.data.entities.TrainingDay
import java.time.LocalDate

@Dao
interface TrainingDayDao {
    @Query("SELECT * FROM TrainingDay")
    fun getAllTrainingDay(): List<TrainingDay>

    @Query("SELECT * FROM TrainingDay WHERE localDate == :date limit 1")
    suspend fun getTrainingDayByDate(date: LocalDate): TrainingDay

    @Insert(onConflict = ABORT)
    suspend fun insertTrainingDay(trainingDay: TrainingDay)

    @Insert(onConflict = ABORT)
    suspend fun insertTrainingDays(trainingDays: List<TrainingDay>)

    @Update
    suspend fun updateTrainingDay(trainingDay: TrainingDay)

    @Query("SELECT COUNT(*) FROM TrainingDay")
    fun count(): Int
}