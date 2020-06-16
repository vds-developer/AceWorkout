//package vds.developer.aceworkout.data.entities
//
//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.Relation
//
//
//data class SetReps(
//        @Embedded
//        var set: Set,
//
//        @Relation(parentColumn = "setId", entityColumn = "repId", entity = Rep::class)
//        var reps : List<Rep>,
//
//        @Relation(parentColumn = "setId", entityColumn = "exerciseId")
//        var exercise: Exercise
//) {
//}