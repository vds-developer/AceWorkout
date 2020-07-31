package vds.developer.aceworkout.data.repository

import android.app.Application
import vds.developer.aceworkout.data.database.SetDao
import vds.developer.aceworkout.data.database.TrainingDataBase
import vds.developer.aceworkout.data.entities.Set


class SetRepository(app: Application) {
    private val setDao: SetDao = TrainingDataBase.getDataBase(app).setDao()

//    fun getAllSetForDay(trainingDay: TrainingDay) : LiveData<List<Set>> {
//        return setDao.getSetByTrainingDayId(trainingDayId = trainingDay.trainingDayId)
//    }

    suspend fun addSet(set: Set) {
        setDao.addSet(set)
    }
}