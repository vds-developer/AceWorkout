package vds.developer.aceworkout.models

import java.util.*

class TrainingDayModel(var sets: MutableList<SetModel>, var date: Date) {
    fun addSet(set: SetModel) {
        sets.add(set)
    }

    fun deleteSet(set: SetModel) {
        sets.remove(set)
    }

    fun deleteSet(index: Int) {
        sets.removeAt(index)
    }
}