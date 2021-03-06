package vds.developer.aceworkout.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import androidx.room.Update
import vds.developer.aceworkout.db.entities.TrainingDayEntity
import java.time.LocalDate

@Dao
interface TrainingDayDao {
    @Query("SELECT * FROM TrainingDay")
    suspend fun getAllTrainingDay(): List<TrainingDayEntity>

    @Query("SELECT * FROM TrainingDay WHERE localDate == :date limit 1")
    suspend fun getTrainingDayByDate(date: LocalDate): TrainingDayEntity

    @Insert(onConflict = ABORT)
    suspend fun insertTrainingDayAsync(trainingDayEntity: TrainingDayEntity)

    @Insert(onConflict = ABORT)
    suspend fun insertTrainingDaySync(trainingDayEntity: TrainingDayEntity) : Long

    @Insert(onConflict = ABORT)
    suspend fun insertTrainingDays(trainingDayEntities: List<TrainingDayEntity>)

    @Update
    suspend fun updateTrainingDay(trainingDayEntity: TrainingDayEntity)

    @Query("SELECT COUNT(*) FROM TrainingDay")
    fun count(): Int

    @Query("SELECT * FROM TrainingDay WHERE localDate >= :date")
    suspend fun getTrainingDayAfter(date: LocalDate) : List<TrainingDayEntity>
}