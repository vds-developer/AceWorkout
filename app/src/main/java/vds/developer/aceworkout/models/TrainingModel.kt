package vds.developer.aceworkout.models

import vds.developer.aceworkout.SingleExerciseStatModel

class TrainingModel (name : String, stats : MutableList<SingleExerciseStatModel>) {
    var name : String = name
    val stats : MutableList<SingleExerciseStatModel> = stats

}