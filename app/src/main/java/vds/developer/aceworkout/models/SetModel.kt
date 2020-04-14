package vds.developer.aceworkout.models

import java.util.*

class SetModel(var name: String, var set: MutableList<SingleRep>, var date: Date) {
    fun getSize() : Int{
        return set.size
    }

    fun addSet(singleRep: SingleRep) {
        set.add(singleRep)
    }
}