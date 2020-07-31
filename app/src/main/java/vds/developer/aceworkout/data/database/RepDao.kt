package vds.developer.aceworkout.data.database

import androidx.room.*
import vds.developer.aceworkout.data.entities.Rep

@Dao
interface RepDao {
    @Query("select * from Rep ORDER BY repId")
    fun getAllRep(): List<Rep>

    @Query("select * from Rep where setId = :setId ORDER BY repId")
    fun getAllRepBySet(setId: Long): List<Rep>

    @Query("select * from Rep where exerciseId = :exerciseId ORDER BY repId")
    fun getAllRepsByExercise(exerciseId: Long): List<Rep>

    @Delete
    suspend fun deleteRep(rep: Rep)

    @Query("update Rep SET weight = :weight, reps = :reps where setId = :setId")
    suspend fun updateRep(weight: Double, reps: Int, setId: Long)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addRep(rep: Rep)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addReps(rep: List<Rep>)
}