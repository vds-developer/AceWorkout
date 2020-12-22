package vds.developer.aceworkout.db.dao

import androidx.room.*
import vds.developer.aceworkout.db.entities.RepEntity

@Dao
interface RepDao {
    @Query("select * from Rep ORDER BY repId")
    suspend fun getAllRep(): List<RepEntity>

    @Query("select * from Rep where setId = :setId ORDER BY repId")
    suspend fun getAllRepBySets(setId: Long): List<RepEntity>

    @Query("select * from Rep where setId in (:setId) ORDER BY repId")
    suspend fun getAllRepBySets(setId: List<Long>): List<RepEntity>

    @Query("select * from Rep where exerciseId = :exerciseId ORDER BY repId")
    suspend fun getAllRepsByExercise(exerciseId: Long): List<RepEntity>

    @Delete
    suspend fun deleteRep(repEntity: RepEntity)

    @Query("update Rep SET weight = :weight, reps = :reps where setId = :setId")
    suspend fun updateRep(weight: Double, reps: Int, setId: Long)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addRep(repEntity: RepEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addReps(repEntity: List<RepEntity>)
}