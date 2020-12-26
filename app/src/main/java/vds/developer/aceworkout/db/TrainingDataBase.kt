package vds.developer.aceworkout.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vds.developer.aceworkout.DummyData.DataGenerator
import vds.developer.aceworkout.DummyData.FundamentalExercises
import vds.developer.aceworkout.db.dao.ExerciseDao
import vds.developer.aceworkout.db.dao.RepDao
import vds.developer.aceworkout.db.dao.SetDao
import vds.developer.aceworkout.db.dao.TrainingDayDao
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.entities.RepEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.db.entities.TrainingDayEntity
import vds.developer.aceworkout.db.util.DateTypeConverter


@Database(entities = [
    TrainingDayEntity::class,
    SetEntity::class,
    RepEntity::class,
    ExerciseEntity::class], version = 1, exportSchema = false)

@TypeConverters(DateTypeConverter::class)

abstract class TrainingDataBase : RoomDatabase() {

    abstract fun trainingDayDao(): TrainingDayDao
    abstract fun setDao(): SetDao
    abstract fun repDao(): RepDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: TrainingDataBase? = null
        fun getDataBase(context: Context): TrainingDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TrainingDataBase::class.java,
                            "training.db"
                    )
//                            .addCallback(PrePopulate)
                            .build()
                }


            }
            INSTANCE!!.populateInitialData()
            return INSTANCE!!
        }

        fun getInstance(context: Context): TrainingDataBase = INSTANCE
                ?: synchronized(this) {
            INSTANCE
                    ?: getDataBase(context).also { INSTANCE = it }
        }

        private val PrePopulate = object : RoomDatabase.Callback() {
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }

    fun populateInitialData() {
        CoroutineScope(Dispatchers.Default).launch {
            INSTANCE?.let {
//                if (INSTANCE!!.trainingDayDao().count() == 0) {
//                    it.trainingDayDao().insertTrainingDays(DataGenerator.getTrainingDay())
//                    it.exerciseDao().addExercises(DataGenerator.getExercise())
//                    it.setDao().addSets(DataGenerator.getSet())
//                    it.repDao().addReps(DataGenerator.getRep())
//                }

                // Attempt to add preliminary exercise
                FundamentalExercises.fundamentalExercise.forEach { exercise ->
                    it.exerciseDao().addExerciseIfNotExist(exercise)
                }

            }
        }
    }
}