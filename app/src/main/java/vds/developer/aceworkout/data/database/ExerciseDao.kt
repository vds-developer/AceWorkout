package vds.developer.aceworkout.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import vds.developer.aceworkout.data.entities.Exercise


@Dao
interface ExerciseDao {
    @Query("select * from Exercise")
    fun getAllExercise():List<Exercise>

    @Query("select * from Exercise where bodyPart = :bodyPart")
    fun getAllExerciseByBodyPart(bodyPart:String):List<Exercise>

    @Query("select * from Exercise where exerciseId = :exerciseId")
    fun getExerciseById(exerciseId:Long):Exercise

    @Insert
    suspend fun addExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

}