package vds.developer.aceworkout.db.repository

import android.app.Application
import vds.developer.aceworkout.db.dao.SetDao
import vds.developer.aceworkout.db.TrainingDataBase
import vds.developer.aceworkout.db.entities.SetEntity


class SetRepository(app: Application) {
    private val setDao: SetDao = TrainingDataBase.getDataBase(app).setDao()

//    fun getAllSetForDay(trainingDay: TrainingDay) : LiveData<List<Set>> {
//        return setDao.getSetByTrainingDayId(trainingDayId = trainingDay.trainingDayId)
//    }

    suspend fun addSet(setEntity: SetEntity) {
        setDao.addSet(setEntity)
    }
}