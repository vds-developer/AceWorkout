package vds.developer.aceworkout.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query("select * from Exercise where exerciseId in (:exerciseId)")
    suspend fun getExerciseByIds(exerciseId: List<Long>): List<ExerciseEntity>

    @Insert
    suspend fun addExercise(exerciseEntity: ExerciseEntity)

    @Query("Select EXISTS(SELECT * FROM Exercise WHERE name = :exerciseName) ")
    suspend fun isExist(exerciseName: String) : Boolean

    @Transaction()
    suspend fun addExerciseIfNotExist(exerciseEntity: ExerciseEntity) {
        if (!isExist(exerciseName = exerciseEntity.name)) {
            addExercise(exerciseEntity)
        }
    }

    @Insert
    suspend fun addExercises(exerciseEntities: List<ExerciseEntity>)

    @Delete
    suspend fun deleteExercise(exerciseEntity: ExerciseEntity)

}