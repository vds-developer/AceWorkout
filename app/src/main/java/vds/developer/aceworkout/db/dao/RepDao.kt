package vds.developer.aceworkout.db.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRep(repEntity: RepEntity)


    @Transaction
    suspend fun upsertRep(repEntity: RepEntity) {
        try {
            addRep(repEntity)
        } catch (exception: SQLiteConstraintException) {
            updateRep(repEntity)
        }
    }

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addReps(repEntity: List<RepEntity>)
}