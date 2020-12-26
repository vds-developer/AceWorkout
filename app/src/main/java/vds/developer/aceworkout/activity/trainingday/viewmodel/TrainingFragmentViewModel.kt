package vds.developer.aceworkout.activity.trainingday.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.entities.RepEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.db.entities.TrainingDayEntity
import vds.developer.aceworkout.db.repository.TrainingDayRepository
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class TrainingFragmentViewModel(application: Application) : AndroidViewModel(application) {

    data class TrainingDaySetsReps(var trainingDayEntity: TrainingDayEntity,
                                   var setEntities: List<SetEntity>,
                                   var repEntities: List<RepEntity>,
                                   var exerciseEntities: List<ExerciseEntity>)

    private var rawData: MutableList<TrainingDaySetsReps> = mutableListOf()
    var trainingDayRepsSets: MutableLiveData<MutableList<TrainingDaySetsReps>> = MutableLiveData(rawData)

    private var trainingDayRepository: TrainingDayRepository = TrainingDayRepository(application)
    var currentDate: LocalDate = LocalDate.now()
    var lastPosition = 0
    private var isUpdating: Boolean = false


    init {
        getLast30Days(currentDate)
    }

    fun setDate(date: LocalDate) {
        this.currentDate = date
    }

//    private fun update() {
//        isUpdating = true
//        var setsForCurrentTrainingDay: List<SetEntity>?
//        var repsForCurrentSet: MutableList<RepEntity>? = null
//        var exerciseEntities: MutableList<ExerciseEntity>? = null
//        viewModelScope.launch {
//            rawData = mutableListOf()
//            // initialize current training day
//            val allTrainingDay = trainingDayRepository.getAllTrainingDay()
//            for (currentTrainingDayEntity in allTrainingDay) {
//                currentTrainingDayEntity.let {
//                    // initialize sets for day
//                    setsForCurrentTrainingDay =
//                            trainingDayRepository.getSetsByTrainingDayId(currentTrainingDayEntity.trainingDayId)
//
//                    // initialize reps for all sets
//                    setsForCurrentTrainingDay?.let {
//                        repsForCurrentSet = mutableListOf<RepEntity>()
//                        for (setEntity: SetEntity in setsForCurrentTrainingDay !!) {
//                            repsForCurrentSet!!.addAll(trainingDayRepository.getRepsBySetId(setEntity.setId))
//                        }
//
//                        repsForCurrentSet?.let {
//                            exerciseEntities = mutableListOf()
//                            for (repEntity: RepEntity in repsForCurrentSet!!) {
//                                exerciseEntities!!.add(trainingDayRepository.getExerciseById(repEntity.exerciseId))
//                            }
//                        }
//                    }
//
//                    currentTrainingDayEntity.let {
//                        //                        if (rawData.filter { t -> t.trainingDayEntity.trainingDayId == it.trainingDayId }.isEmpty()) {
//                        rawData.add(
//                                TrainingDaySetsReps(
//                                        currentTrainingDayEntity,
//                                        setsForCurrentTrainingDay,
//                                        repsForCurrentSet?.toList(),
//                                        exerciseEntities?.toList()))
//                    }
//                }
//            }
//            if (rawData.isNotEmpty()) {
//                rawData.sortBy {
//                    it.trainingDayEntity.dateTime.localDate
//                }
//            }
//            trainingDayRepsSets.postValue(rawData)
//            isUpdating = false
//
//        }
//    }

//    private fun update(date: LocalDate) {
//        isUpdating = true
//        var currentTrainingDayEntity: TrainingDayEntity?
//        var setsForCurrentTrainingDay: List<SetEntity>?
//        var repsForCurrentSet: MutableList<RepEntity>? = null
//        var exerciseEntities: MutableList<ExerciseEntity>? = null
//        viewModelScope.launch {
//            // initialize current training day
//            currentTrainingDayEntity = trainingDayRepository.getTrainingDay(date)
//
//            currentTrainingDayEntity.let {
//                // initialize sets for day
//                setsForCurrentTrainingDay = trainingDayRepository.getSetsByTrainingDayId(currentTrainingDayEntity !!.trainingDayId)
//
//                // initialize reps for all sets
//                setsForCurrentTrainingDay?.let {
//                    repsForCurrentSet = mutableListOf<RepEntity>()
//                    for (setEntity: SetEntity in setsForCurrentTrainingDay!!) {
//                        repsForCurrentSet!!.addAll(trainingDayRepository.getRepsBySetId(setEntity.setId))
//                    }
//
//                    repsForCurrentSet?.let {
//                        exerciseEntities = mutableListOf()
//                        for (repEntity: RepEntity in repsForCurrentSet!!) {
//                            exerciseEntities!!.add(trainingDayRepository.getExerciseById(repEntity.exerciseId))
//                        }
//                    }
//                }
//
//                currentTrainingDayEntity.let {
//                    if (it != null) {
//                        if (trainingDayRepsSets.value!!.filter { t -> t.trainingDayEntity.trainingDayId == it.trainingDayId }.isEmpty()) {
//                            rawData.add(
//                                    TrainingDaySetsReps(
//                                            currentTrainingDayEntity !!,
//                                            setsForCurrentTrainingDay,
//                                            repsForCurrentSet?.toList(),
//                                            exerciseEntities?.toList()))
//                            trainingDayRepsSets.value = rawData
//                            //                                    trainingDayRepsSets.postValue(rawData)
//                        }
//                    }
//                }
//    //                        }
//            }
//            isUpdating = false
//        }
//        if (trainingDayRepsSets.value!!.isNotEmpty()) {
//            trainingDayRepsSets.value!!.sortBy {
//                it.trainingDayEntity.dateTime.localDate
//            }
//        }
//    }

    private fun getLast30Days(date: LocalDate) {
        isUpdating = true
        viewModelScope.launch {
            // initialize current training day
            val trainingDayEntities = trainingDayRepository.getLast30DayTrainingDay(date)
            val sets = trainingDayRepository.getSetsByTrainingDayIds(trainingDayEntities.map { e -> e.trainingDayId }.distinct())
            val reps = trainingDayRepository.getRepsBySetIds(sets.map { e -> e.setId }.distinct())
            val exercise = trainingDayRepository.getExerciseByIds(sets.map { e -> e.exerciseId }.distinct())
            rawData = mutableListOf()
            trainingDayRepsSets.value = rawData

            for (trainingDay in trainingDayEntities) {
                val currentSets = sets.let {
                    sets.filter { e -> trainingDay.trainingDayId == e.trainingDayId }
                }
                val currentReps = reps.let {
                    reps.filter { e -> currentSets.stream().anyMatch { match -> match.setId == e.setId } }
                }

                val currentExercise = exercise.let {
                    exercise.filter { e -> currentSets.stream().anyMatch { match -> match.exerciseId == e.exerciseId } }
                }

                trainingDayEntities.let {
                        rawData.add(
                                TrainingDaySetsReps(
                                        trainingDay,
                                        currentSets.toList(),
                                        currentReps.toList(),
                                        currentExercise.toList())
                        )
                        trainingDayRepsSets.value = rawData
                    }
            }
            isUpdating = false
        }
        if (trainingDayRepsSets.value !!.isNotEmpty()) {
            trainingDayRepsSets.value !!.sortBy {
                it.trainingDayEntity.dateTime.localDate
            }
        }
    }

    fun refresh() {
        getLast30Days(currentDate)
    }

    fun getTrainingDaySetsRepsByDate(date: LocalDate): TrainingDaySetsReps? {
        return if (trainingDayRepsSets.value == null ||
                trainingDayRepsSets.value !!.filter { e -> e.trainingDayEntity.dateTime.localDate == date }.isEmpty()) null
        else {
            trainingDayRepsSets.value !!.filter { e -> e.trainingDayEntity.dateTime.localDate == date }[0]
        }

    }

        fun addRep(repEntity: RepEntity): Boolean {
            return viewModelScope.launch {
                trainingDayRepository.insertRep(repEntity)
                getLast30Days(currentDate)
            }.isCompleted
        }

        fun editRep(repEntity: RepEntity): Boolean {
            return viewModelScope.launch {
                trainingDayRepository.updateRep(repEntity)
                getLast30Days(currentDate)
            }.isCompleted
        }

        fun deleteRep(repEntity: RepEntity): Boolean {
            return viewModelScope.launch {
                trainingDayRepository.deleteRep(repEntity)
                getLast30Days(currentDate)
            }.isCompleted
        }

        fun getTrainingDayByDate(searchDate: LocalDate): TrainingDaySetsReps? {
            if (trainingDayRepsSets.value !!.any { it.trainingDayEntity.dateTime.localDate == searchDate }) {
                return trainingDayRepsSets.value !!.first { it.trainingDayEntity.dateTime.localDate == searchDate }
            }
            return null
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


        data class TrainingFragmentViewModelData(var date: LocalDate) {

        }
}
