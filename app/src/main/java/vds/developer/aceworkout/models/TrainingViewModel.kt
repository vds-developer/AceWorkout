package vds.developer.aceworkout.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vds.developer.aceworkout.SingleExerciseStatModel
import vds.developer.aceworkout.TrainingRepository

class TrainingViewModel() : ViewModel() {
    val trainingList = MutableLiveData<MutableList<TrainingModel>>()

    init {
        trainingList.value = TrainingRepository.getTrainingData()!!
    }

    fun addSet(index : Int, set : SingleExerciseStatModel) {
        trainingList.value!![index].stats.add(set)
    }

}
