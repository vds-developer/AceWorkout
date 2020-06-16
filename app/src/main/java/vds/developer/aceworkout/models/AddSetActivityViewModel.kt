package vds.developer.aceworkout.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import vds.developer.aceworkout.data.entities.Exercise
import vds.developer.aceworkout.data.repository.ExerciseRepository
import vds.developer.aceworkout.data.repository.TrainingDayRepository


class AddSetActivityViewModel(app : Application) : AndroidViewModel(app) {

    lateinit var allExercise : LiveData<List<Exercise>>
    private var exerciseRepository : ExerciseRepository = ExerciseRepository(app)
    init {
        getAllExercise()
    }

    private fun getAllExercise() {
//        allExercise = exerciseRepository.getAllExerciseLiveData()
    }


}