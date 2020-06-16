package vds.developer.aceworkout.data.database

import android.content.Context
import androidx.room.*
import vds.developer.aceworkout.data.entities.Exercise
import vds.developer.aceworkout.data.entities.Rep
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.data.entities.TrainingDay
import vds.developer.aceworkout.data.util.DateTypeConverter

@Database(entities= [
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

    companion object{
        @Volatile
        private var INSTANCE: TrainingDataBase? = null
        fun getDataBase(context: Context) : TrainingDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TrainingDataBase:: class.java,
                            "training.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}