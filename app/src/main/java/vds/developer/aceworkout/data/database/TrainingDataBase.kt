package vds.developer.aceworkout.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vds.developer.aceworkout.data.entities.Exercise
import vds.developer.aceworkout.data.entities.Rep
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.data.entities.TrainingDay
import vds.developer.aceworkout.data.util.DateTypeConverter


@Database(entities = [
    TrainingDay::class,
    Set::class,
    Rep::class,
    Exercise::class], version = 1, exportSchema = false)

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

        fun getInstance(context: Context): TrainingDataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: getDataBase(context).also { INSTANCE = it }
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
                if (INSTANCE!!.trainingDayDao().count() == 0) {
                    it.trainingDayDao().insertTrainingDays(DataGenerator.getTrainingDay())
                    it.exerciseDao().addExercises(DataGenerator.getExercise())
                    it.setDao().addSets(DataGenerator.getSet())
                    it.repDao().addReps(DataGenerator.getRep())
                }
            }
        }
    }
}