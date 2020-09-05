package vds.developer.aceworkout.db.repository

import android.app.Application
import vds.developer.aceworkout.db.dao.RepDao
import vds.developer.aceworkout.db.TrainingDataBase

class RepRepository(app: Application) {
    private val repDao: RepDao = TrainingDataBase.getDataBase(app).repDao()

//    fun getAllRepForSet(set : Set) : MutableLiveData<List<Rep>> {
//        return repDao.getAllRepBySet(set.setId)
//    }
}