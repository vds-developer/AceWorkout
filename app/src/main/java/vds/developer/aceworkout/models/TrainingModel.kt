package vds.developer.aceworkout.models

import vds.developer.aceworkout.SingleExerciseStatModel
import java.util.*

class TrainingModel(name: String, stats: MutableList<SingleExerciseStatModel>, date: Date) {
    var name : String = name
    var date : Date = date
    val stats : MutableList<SingleExerciseStatModel> = stats

}