package vds.developer.aceworkout.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import vds.developer.aceworkout.data.database.TrainingDataBase
import vds.developer.aceworkout.data.entities.Exercise

class ExerciseRepository(val app : Application) {
    private val exerciseDao = TrainingDataBase.getDataBase(app).exerciseDao()

    fun getAllExerciseLiveData() : LiveData<List<Exercise>> {
        return exerciseDao.getAllExerciseLiveData()
    }



}