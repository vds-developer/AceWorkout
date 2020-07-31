package vds.developer.aceworkout.data.repository

import android.app.Application
import vds.developer.aceworkout.data.database.RepDao
import vds.developer.aceworkout.data.database.TrainingDataBase

class RepRepository(app: Application) {
    private val repDao: RepDao = TrainingDataBase.getDataBase(app).repDao()

//    fun getAllRepForSet(set : Set) : MutableLiveData<List<Rep>> {
//        return repDao.getAllRepBySet(set.setId)
//    }
}