package vds.developer.aceworkout.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import vds.developer.aceworkout.data.entities.Rep

@Dao
interface RepDao {
    @Query("select * from Rep ORDER BY repId")
    fun getAllRep(): List<Rep>

    @Query("select * from Rep where setId = :setId ORDER BY repId")
    fun getAllRepBySet(setId : Long) : List<Rep>

    @Query("select * from Rep where exerciseId = :exerciseId ORDER BY repId")
    fun getAllRepsByExercise(exerciseId:Long) : List<Rep>

    @Delete
    suspend fun deleteRep(rep: Rep)

    @Query("update Rep SET weight = :weight, reps = :reps where setId = :setId")
    suspend fun updateRep(weight: Double, reps: Int, setId: Long)

    @Insert
    suspend fun insertRep(rep: Rep)
}