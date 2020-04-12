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

    fun deleteSet(index: Int, set : SingleExerciseStatModel) {
        trainingList.value!![index].stats.removeAt(0)
    }

    fun getTrainingModel(index : Int) : TrainingModel? {
        return trainingList.value?.get(index)
    }

    fun getSizeForTrainingModel(index : Int) : Int {
        return if(getTrainingModel(index) == null || getTrainingModel(index)?.stats == null) 0 else getTrainingModel(index)!!.stats.size
    }

}
