package vds.developer.aceworkout.pages.trainingFragment

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


//@RequiresApi(Build.VERSION_CODES.O)
@RequiresApi(Build.VERSION_CODES.O)
class TrainingFragmentViewModel(application: Application) : AndroidViewModel(application) {

    data class TrainingDaySetsReps(var trainingDayEntity: TrainingDayEntity,
                                   var setEntities: List<SetEntity>?,
                                   var repEntities: List<RepEntity>?,
                                   var exerciseEntities: List<ExerciseEntity>?)

    var rawData: MutableList<TrainingDaySetsReps> = mutableListOf()
    var trainingDayRepsSets: MutableLiveData<MutableList<TrainingDaySetsReps>> = MutableLiveData(rawData)


    private var currentTrainingDayEntity: TrainingDayEntity? = null
    private var setsForCurrentTrainingDay: List<SetEntity>? = null
    private var repsForCurrentSet: MutableList<RepEntity>? = null
    private var exerciseEntities: MutableList<ExerciseEntity>? = null

    private var trainingDayRepository: TrainingDayRepository = TrainingDayRepository(application)
    var date: LocalDate
    var currentPosition : Int = 0
    var isUpdating: Boolean = false

    init {
        this.date = LocalDate.now().plusDays(1)
        update()

//        trainingDayRepsSets.value = TrainingDaySetsReps(
//                TrainingDayGenerator.trainingDayEntities[0],
//                TrainingDayGenerator.sets,
//                TrainingDayGenerator.reps,
//                TrainingDayGenerator.exercise
//        )


//        update(date)
    }

    fun getNext() {
        this.date = date.plusDays(1)
        // update and get next date
        if (getTrainingDayByDate(this.date) == null) update(date)


    }

    fun getPrev() {
        this.date = date.minusDays(1)
        if (getTrainingDayByDate(this.date) == null) update(date)

    }

//    fun update() {
//        update(date)
//    }

    private fun update() {
        isUpdating = true
        viewModelScope.launch {
            rawData = mutableListOf()
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // initialize current training day
            val allTrainingDay = trainingDayRepository.getAllTrainingDay()
            for (currentTrainingDayEntity in allTrainingDay) {
                currentTrainingDayEntity?.let {
                    // initialize sets for day
                    var setsForCurrentTrainingDay =
                            trainingDayRepository.getSetsByTrainingDayId(currentTrainingDayEntity.trainingDayId)

                    // initialize reps for all sets
                    setsForCurrentTrainingDay?.let {
                        repsForCurrentSet = mutableListOf<RepEntity>()
                        for (setEntity: SetEntity in setsForCurrentTrainingDay) {
                            repsForCurrentSet!!.addAll(trainingDayRepository.getRepsBySetId(setEntity.setId))
                        }

                        repsForCurrentSet?.let {
                            exerciseEntities = mutableListOf()
                            for (repEntity: RepEntity in repsForCurrentSet!!) {
                                exerciseEntities!!.add(trainingDayRepository.getExerciseById(repEntity.exerciseId))
                            }
                        }
                    }

                    currentTrainingDayEntity?.let {
//                        if (rawData.filter { t -> t.trainingDayEntity.trainingDayId == it.trainingDayId }.isEmpty()) {
                            rawData.add(
                                    TrainingDaySetsReps(
                                            currentTrainingDayEntity!!,
                                            setsForCurrentTrainingDay,
                                            repsForCurrentSet?.toList(),
                                            exerciseEntities?.toList()))

//                        }
                    }
                }


            }
            if (rawData.isNotEmpty()) {
                rawData.sortBy {
                    it.trainingDayEntity.dateTime.localDate
                }
            }
            trainingDayRepsSets.postValue(rawData)
            isUpdating = false

        }
    }

    private fun update(date: LocalDate) {

        currentTrainingDayEntity = null
        setsForCurrentTrainingDay = null
        repsForCurrentSet = null
        exerciseEntities = null
        isUpdating = true

        viewModelScope.launch {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // initialize current training day
            currentTrainingDayEntity = trainingDayRepository.getTrainingDay(date)

            currentTrainingDayEntity?.let {
                // initialize sets for day
                setsForCurrentTrainingDay = trainingDayRepository.getSetsByTrainingDayId(currentTrainingDayEntity!!.trainingDayId)

                // initialize reps for all sets
                setsForCurrentTrainingDay?.let {
                    repsForCurrentSet = mutableListOf<RepEntity>()
                    for (setEntity: SetEntity in setsForCurrentTrainingDay!!) {
                        repsForCurrentSet!!.addAll(trainingDayRepository.getRepsBySetId(setEntity.setId))
                    }

                    repsForCurrentSet?.let {
                        exerciseEntities = mutableListOf()
                        for (repEntity: RepEntity in repsForCurrentSet!!) {
                            exerciseEntities!!.add(trainingDayRepository.getExerciseById(repEntity.exerciseId))
                        }
                    }
                }

                currentTrainingDayEntity?.let {
                    if (trainingDayRepsSets.value!!.filter { t -> t.trainingDayEntity.trainingDayId == it.trainingDayId }.isEmpty()) {
                        rawData.add(
                                TrainingDaySetsReps(
                                        currentTrainingDayEntity!!,
                                        setsForCurrentTrainingDay,
                                        repsForCurrentSet?.toList(),
                                        exerciseEntities?.toList()))
                        trainingDayRepsSets.value = rawData
//                                    trainingDayRepsSets.postValue(rawData)
                    }
                }
//                        }
            }
            isUpdating = false
        }
        if (trainingDayRepsSets.value!!.isNotEmpty()) {
            trainingDayRepsSets.value!!.sortBy {
                it.trainingDayEntity.dateTime.localDate
            }
        }
    }


    fun refresh() {
        update(date)
    }

    fun addTrainingDay() {

    }

    fun addSet() {

    }

//    fun <T> MutableLiveData<T>.notifyObserver() {
//        this.value = this.value
//    }

    fun addRep(repEntity: RepEntity): Boolean {
//        viewModelScope.launch {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                trainingDayRepository.insertRep(rep)
//            }
//        }
        var isSuccess = false
        getTrainingDayByDate(this.date)?.let { model ->
            if (model.repEntities == null) model.repEntities = emptyList()
            val newReps = model.repEntities!!.toMutableList()
            isSuccess = newReps.add(repEntity)
//            trainingDayRepsSets.notifyObserver()
            viewModelScope.launch {
                trainingDayRepository.insertRep(repEntity)
                update()
            }
        }
        return isSuccess

    }

    fun editRep(repEntity: RepEntity): Boolean {
        var isSuccess = false
        val repId = repEntity.repId
        getTrainingDayByDate(this.date)?.let { model ->
            if (model.repEntities == null) model.repEntities = emptyList()
            val modifyRep = model.repEntities!!.toMutableList()
            val removeRep = modifyRep.stream().filter { rep -> rep.repId == repId }.findFirst().orElse(RepEntity(0, 0, -1, 0.0, 0, 0))
            modifyRep.remove(removeRep)
            isSuccess = modifyRep.add(repEntity)
        }
        return isSuccess
    }

    fun deleteRep(repEntity: RepEntity): Boolean {
        val repId = repEntity.repId
        var isSuccess = false
        getTrainingDayByDate(this.date)?.let { model ->
            val modifyRep = model.repEntities!!.toMutableList()
            val removeRep = modifyRep.stream().filter { rep -> rep.repId == repId }.findFirst().orElse(RepEntity(0, 0, -1, 0.0, 0, 0))
            isSuccess = modifyRep.remove(removeRep)
        }
        return isSuccess

    }

    private fun getTrainingDayByDate(searchDate: LocalDate): TrainingDaySetsReps? {
        if (trainingDayRepsSets.value!!.any { it.trainingDayEntity.dateTime.localDate == searchDate }) {
            return trainingDayRepsSets.value!!.first { it.trainingDayEntity.dateTime.localDate == searchDate }
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

}
