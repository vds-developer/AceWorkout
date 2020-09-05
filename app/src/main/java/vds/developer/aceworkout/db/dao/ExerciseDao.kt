package vds.developer.aceworkout.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import vds.developer.aceworkout.db.entities.ExerciseEntity


@Dao
interface ExerciseDao {
    @Query("select * from Exercise")
    suspend fun getAllExercise(): List<ExerciseEntity>

    @Query("select * from Exercise")
    suspend fun getAllExerciseLiveData(): List<ExerciseEntity>

    @Query("select * from Exercise where bodyPart = :bodyPart")
    suspend fun getAllExerciseByBodyPart(bodyPart: String): List<ExerciseEntity>

    @Query("select * from Exercise where exerciseId = :exerciseId")
    suspend fun getExerciseById(exerciseId: Long): ExerciseEntity

    @Insert
    suspend fun addExercise(exerciseEntity: ExerciseEntity)

    @Insert
    suspend fun addExercises(exerciseEntities: List<ExerciseEntity>)

    @Delete
    suspend fun deleteExercise(exerciseEntity: ExerciseEntity)

}