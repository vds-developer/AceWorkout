package vds.developer.aceworkout.pages.addSet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.db.repository.ExerciseRepository
import vds.developer.aceworkout.db.repository.SetRepository

class SelectExerciseViewModel(app: Application) : AndroidViewModel(app) {
    lateinit var exerciseEntity: LiveData<List<ExerciseEntity>>
    private var exerciseRepository: ExerciseRepository = ExerciseRepository(app)
    private var setRepository: SetRepository = SetRepository(app)

    init {
        getFilteredExercisesByBodyPart("all")
    }

    private fun getFilteredExercisesByBodyPart(bodyPart: String) {
        exerciseEntity = MutableLiveData<List<ExerciseEntity>>().apply {
            postValue(mutableListOf(
                    ExerciseEntity(0, "Exercise 1", "Back", true),
                    ExerciseEntity(0, "Exercise 2", "Back", true),
                    ExerciseEntity(0, "Exercise 3", "Legs", true),
                    ExerciseEntity(0, "Exercise 4", "Arm", true)
            ).filter { e -> e.bodyPart == bodyPart }
            )
        }
    }

    fun updateFilter(bodyPart: String) {
        getFilteredExercisesByBodyPart(bodyPart)
    }

    fun addSet(setEntity: SetEntity) {
//        viewModelScope.launch {
//            setRepository.addSet(set)
//        }
    }
}