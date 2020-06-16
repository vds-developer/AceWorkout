package vds.developer.aceworkout.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vds.developer.aceworkout.data.database.SetDao
import vds.developer.aceworkout.data.database.TrainingDataBase
import vds.developer.aceworkout.data.entities.TrainingDay
import vds.developer.aceworkout.data.entities.Set


class SetRepository(app: Application) {
    private val setDao : SetDao = TrainingDataBase.getDataBase(app).setDao()

//    fun getAllSetForDay(trainingDay: TrainingDay) : LiveData<List<Set>> {
//        return setDao.getSetByTrainingDayId(trainingDayId = trainingDay.trainingDayId)
//    }
}