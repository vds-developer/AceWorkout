package vds.developer.aceworkout.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vds.developer.aceworkout.TrainingRepository
import java.util.*

class TrainingViewModel() : ViewModel() {
    val trainingList = MutableLiveData<MutableList<TrainingDayModel>>()

    init {
        trainingList.value = TrainingRepository.getTrainingData()!!
    }

    fun getTrainingDay(index : Int): TrainingDayModel {
        return trainingList.value!![index]
    }

    fun getTrainingModel(index : Int) : SetModel? {
//        return trainingList.value?.get(index)
        return null
    }

    fun getTotalDays() : Int {
        return trainingList.value!!.size
//        return if(getTrainingModel(index) == null || getTrainingModel(index)?.set == null) 0 else getTrainingModel(index)!!.set.size
    }

    fun getDate(index : Int) : Date{
        return trainingList.value!![index].date
    }

}
