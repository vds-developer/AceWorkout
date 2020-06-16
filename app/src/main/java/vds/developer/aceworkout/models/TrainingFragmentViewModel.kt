package vds.developer.aceworkout.models

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import vds.developer.aceworkout.DummyData.TrainingDayGenerator
import vds.developer.aceworkout.data.entities.Exercise
import vds.developer.aceworkout.data.entities.Rep
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.data.entities.TrainingDay
import vds.developer.aceworkout.data.repository.TrainingDayRepository
import java.time.LocalDate

//@RequiresApi(Build.VERSION_CODES.O)
@RequiresApi(Build.VERSION_CODES.O)
class TrainingFragmentViewModel (application: Application) : AndroidViewModel(application) {

    public data class TrainingDaySetsReps(var trainingDay: TrainingDay, var sets: List<Set>, var reps : List<Rep>, var exercises: List<Exercise> ){

    }

    public var trainingDayRepsSets : MutableLiveData<TrainingDaySetsReps> = MutableLiveData();

    private lateinit var currentTrainingDay: TrainingDay
    private lateinit var setsForCurrentTrainingDay: List<Set>
    private lateinit var repsForCurrentSet : MutableList<Rep>
    private lateinit var exercises : MutableList<Exercise>

    private var trainingDayRepository : TrainingDayRepository = TrainingDayRepository(application)
    lateinit var date : LocalDate

    init {
        trainingDayRepsSets = MutableLiveData()

        trainingDayRepsSets?.value = TrainingDaySetsReps(
                TrainingDayGenerator.trainingDays[0],
                TrainingDayGenerator.sets,
                TrainingDayGenerator.reps,
                TrainingDayGenerator.exercise
        )

        this.date = LocalDate.now()
//        update(date)
    }

    fun update(date : LocalDate) {
        this.date = date
        viewModelScope.launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // initialize current training day
                currentTrainingDay = trainingDayRepository.getTrainingDay(date)

                // initialize sets for day
                setsForCurrentTrainingDay =  trainingDayRepository.getSetsByTrainingDayId(currentTrainingDay.trainingDayId)

                // initialize reps for all sets
                repsForCurrentSet = mutableListOf<Rep>()
                for (set: Set in setsForCurrentTrainingDay) {
                    repsForCurrentSet.addAll(trainingDayRepository.getRepsBySetId(set.setId))
                }
                exercises = mutableListOf<Exercise>()
                for (rep: Rep in repsForCurrentSet) {
                    exercises.add(trainingDayRepository.getExerciseById(rep.exerciseId))
                }
                trainingDayRepsSets!!.value = TrainingDaySetsReps(currentTrainingDay, setsForCurrentTrainingDay, repsForCurrentSet.toList(), exercises.toList())
            }
        }
    }

    fun refresh() {
        TrainingDayGenerator.Regenerate()
        trainingDayRepsSets?.value = TrainingDaySetsReps(
                TrainingDayGenerator.trainingDays[0],
                TrainingDayGenerator.sets,
                TrainingDayGenerator.reps,
                TrainingDayGenerator.exercise
        )
    }

    fun addTrainingDay() {

    }

    fun addSet() {

    }

    fun addRep(rep : Rep) {
//        viewModelScope.launch {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                trainingDayRepository.insertRep(rep)
//            }
//        }

        var model = trainingDayRepsSets?.value
        var newReps = mutableListOf<Rep>()
        newReps  = model!!.reps?.let {it -> it.toMutableList() }
        val add = newReps.add(rep)
        trainingDayRepsSets?.value = TrainingDaySetsReps(
                model.trainingDay,
                model.sets,
                newReps.toList(),
                model.exercises
        )
    }

    fun editRep (rep : Rep) {
        var repId = rep.repId
        var model = trainingDayRepsSets?.value
        var modifyRep = mutableListOf<Rep>()
        modifyRep  = model!!.reps?.let {it -> it.toMutableList() }
        var removeRep = modifyRep.stream().filter { rep -> rep.repId == repId }.findFirst().orElse(Rep(0,0,-1,0.0,0,0))
        modifyRep.remove(removeRep)
        modifyRep.add(rep)

        trainingDayRepsSets?.value = TrainingDaySetsReps(
                model.trainingDay,
                model.sets,
                modifyRep.toList(),
                model.exercises
        )
    }

    fun deleteRep(rep : Rep) {
        var repId = rep.repId
        var model = trainingDayRepsSets?.value
        var modifyRep = mutableListOf<Rep>()
        modifyRep  = model!!.reps?.let {it -> it.toMutableList() }
        var removeRep = modifyRep.stream().filter { rep -> rep.repId == repId }.findFirst().orElse(Rep(0,0,-1,0.0,0,0))
        modifyRep.remove(removeRep)
        trainingDayRepsSets?.value = TrainingDaySetsReps(
                model.trainingDay,
                model.sets,
                modifyRep.toList(),
                model.exercises
        )
    }

//
//    fun getTrainingDay(index : Int): TrainingDayModel {
//        return trainingList.value!![index]
//    }
//
//    fun getTrainingModel(index : Int) : SetModel? {
////        return trainingList.value?.get(index)
//        return null
//    }
//
//    fun getTotalDays() : Int {
//        return trainingList.value!!.size
////        return if(getTrainingModel(index) == null || getTrainingModel(index)?.set == null) 0 else getTrainingModel(index)!!.set.size
//    }
//
//    fun getDate(index : Int) : Date{
//        return trainingList.value!![index].date
//    }

}
