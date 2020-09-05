package vds.developer.aceworkout.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import vds.developer.aceworkout.db.TrainingDataBase
import vds.developer.aceworkout.db.entities.ExerciseEntity

class ExerciseRepository(val app: Application) {
    private val exerciseDao = TrainingDataBase.getDataBase(app).exerciseDao()

//    fun getAllExerciseLiveData(): List<ExerciseEntity> {
//        return exerciseDao.getAllExerciseLiveData()
//    }


}