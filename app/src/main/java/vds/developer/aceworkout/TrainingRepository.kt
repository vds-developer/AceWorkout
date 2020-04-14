package vds.developer.aceworkout

import androidx.lifecycle.MutableLiveData
import vds.developer.aceworkout.models.SetModel
import vds.developer.aceworkout.models.SingleRep
import vds.developer.aceworkout.models.TrainingDayModel
import java.util.*

class TrainingRepository {

    companion object {
        fun getTrainingData() : MutableList<TrainingDayModel> {
            MutableLiveData<MutableList<TrainingDayModel>>()
            val sets = mutableListOf<SetModel>()
            val reps = mutableListOf<SingleRep>()
            val day = mutableListOf<TrainingDayModel>()

            var date = Date()
            for (i in 1..4) {
                reps.add(SingleRep(3.0, 2))
            }
            for ( i in 1..3) {
                sets.add(SetModel("Set $i", reps, date))
            }
            for (i in 1..3) {
                day.add(TrainingDayModel( sets, date))
            }
            return day
        }


    }

}