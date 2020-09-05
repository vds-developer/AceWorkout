package vds.developer.aceworkout.pages.addSet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.repository.ExerciseRepository


class AddSetActivityViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var allExerciseEntity: LiveData<List<ExerciseEntity>>
    private var exerciseRepository: ExerciseRepository = ExerciseRepository(app)

    init {
        getAllExercise()
    }

    private fun getAllExercise() {
//        allExercise = exerciseRepository.getAllExerciseLiveData()
    }


}