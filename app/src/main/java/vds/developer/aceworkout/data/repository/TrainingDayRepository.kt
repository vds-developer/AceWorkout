package vds.developer.aceworkout.data.repository

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vds.developer.aceworkout.data.database.TrainingDataBase
import vds.developer.aceworkout.data.entities.*
import vds.developer.aceworkout.data.entities.Set
import java.time.LocalDate

//@RequiresApi(Build.VERSION_CODES.O)
class TrainingDayRepository(val app: Application) {

    private val trainingDayDao = TrainingDataBase.getDataBase(app).trainingDayDao()
    private val setDao = TrainingDataBase.getDataBase(app).setDao()
    private val repDao = TrainingDataBase.getDataBase(app).repDao()
    private val exerciseDao = TrainingDataBase.getDataBase(app).exerciseDao()


//    lateinit var date : LocalDate;

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            date = LocalDate.now()
        }
    }

     suspend fun getTrainingDay(date:LocalDate) : TrainingDay {
         return trainingDayDao.getTrainingDayByDate(date)
    }

    suspend fun getSetsByTrainingDayId(trainingId : Long) : List<Set> {
        return setDao.getSetByTrainingDayId(trainingId)
    }

    suspend fun getRepsBySetId(setId : Long) : List<Rep> {
        return repDao.getAllRepBySet(setId)
    }

    suspend fun getExerciseById(exerciseId : Long) : Exercise {
        return exerciseDao.getExerciseById(exerciseId)
    }

    suspend fun insertRep(rep: Rep) {
        repDao.insertRep(rep)
    }


//    suspend fun updateRepository(date:LocalDate) {
//
//    }

//
//    companion object {
//        fun getTrainingData() : MutableList<TrainingDayModel> {
//            MutableLiveData<MutableList<TrainingDayModel>>()
//            val sets = mutableListOf<SetModel>()
//            val reps = mutableListOf<SingleRep>()
//            val day = mutableListOf<TrainingDayModel>()
//
//            var date = Date()
//            for (i in 1..4) {
//                reps.add(SingleRep(3.0, 2))
//            }
//            for ( i in 1..3) {
//                sets.add(SetModel("Set $i", reps, date))
//            }
//            for (i in 1..3) {
//                day.add(TrainingDayModel( sets, date))
//            }
//            return day
//        }
//
//
//    }

}