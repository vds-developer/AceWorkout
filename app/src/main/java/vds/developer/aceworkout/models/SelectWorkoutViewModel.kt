package vds.developer.aceworkout.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import vds.developer.aceworkout.data.entities.Exercise
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.data.repository.ExerciseRepository
import vds.developer.aceworkout.data.repository.SetRepository
import java.time.LocalDate

class SelectWorkoutViewModel(app:Application) : AndroidViewModel(app) {
    lateinit var exercise : LiveData<List<Exercise>>
    private var exerciseRepository : ExerciseRepository = ExerciseRepository(app)
    private var setRepository : SetRepository = SetRepository(app);
    init {
        getFilteredExercisesByBodyPart("all")
    }

    private fun getFilteredExercisesByBodyPart(bodyPart : String) {
        exercise = MutableLiveData<List<Exercise>>().apply {
            postValue(  mutableListOf(
                    Exercise(0, "Exercise 1", "Back", true),
                    Exercise(0, "Exercise 2", "Back", true),
                    Exercise(0, "Exercise 3", "Legs", true),
                    Exercise(0, "Exercise 4", "Arm", true)
            ).filter { e -> e.bodyPart == bodyPart }
            )
        }





//                .apply { it ->
//            it.postValue(
//                    mutableListOf(
//                            Exercise(0, "Exercise 1", "Back", true),
//                            Exercise(0, "Exercise 2", "Back", true),
//                            Exercise(0, "Exercise 3", "Legs", true),
//                            Exercise(0, "Exercise 4", "Arm", true)
//
//                    )
//            )
//        }.
//        allExercise = exerciseRepository.getAllExerciseLiveData()
    }

    fun updateFilter(bodyPart: String) {
        getFilteredExercisesByBodyPart(bodyPart);
    }

    fun addSet(set : Set) {
//        viewModelScope.launch {
//            setRepository.addSet(set)
//        }

    }
}