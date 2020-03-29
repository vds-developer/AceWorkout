package vds.developer.aceworkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import vds.developer.aceworkout.models.TrainingModel
import java.util.*

class TrainingRepository {

    companion object {
        fun getTrainingData() : MutableList<TrainingModel> {
            MutableLiveData<MutableList<TrainingModel>>()
            val list1 = mutableListOf<TrainingModel>()
            val list2 = mutableListOf<SingleExerciseStatModel>()
            for (i in 1..4) {
                list2.add(SingleExerciseStatModel(3.0, 2))
            }

            for (i in 1..3) {
                list1.add(TrainingModel("Training item $i", list2))
            }
            return list1
        }


    }

}