package vds.developer.aceworkout.activity.addset.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vds.developer.aceworkout.db.entities.DateTimeEntity
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.db.entities.TrainingDayEntity
import vds.developer.aceworkout.db.repository.ExerciseRepository
import vds.developer.aceworkout.db.repository.SetRepository
import vds.developer.aceworkout.db.repository.TrainingDayRepository
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

class SelectExerciseViewModel(app: Application) : AndroidViewModel(app) {
    var exerciseEntity:MutableLiveData<List<ExerciseEntity>> = MutableLiveData()
    private var exerciseRepository: ExerciseRepository = ExerciseRepository(app)
    private var setRepository: SetRepository = SetRepository(app)
    private var trainingDayRepository: TrainingDayRepository = TrainingDayRepository(app)
    var allExerciseEntity: List<ExerciseEntity> = emptyList()
    var loading : Boolean = false

    init {
//        getAllExercises()
//        while (loading) {
//            Thread.sleep(100)
//        }
    }

    private fun getFilteredExercisesByBodyPart(bodyPart: String) {
//        if (exerciseEntity.value == null ) return emptyList()
//        return exerciseEntity.value!!.let {
//            it.filter { e -> e.bodyPart == bodyPart }
//        }
        viewModelScope.launch {
            exerciseEntity.postValue(exerciseRepository.getAllExerciseByBodyPart(bodyPart))
        }
//        exerciseEntity.run { postValue(allExerciseEntity.filter { e -> e.bodyPart == bodyPart }) }
    }


//    private fun getAllExercises () {
//        viewModelScope.launch {
//            allExerciseEntity = exerciseRepository.getAllExerciseLiveData()
//        }
//    }

    fun updateFilter(bodyPart: String) {
        getFilteredExercisesByBodyPart(bodyPart)
    }

    fun addSetOrAddTrainingDayAndSet(setEntity: SetEntity, trainingDayDate : LocalDate ) {
        viewModelScope.launch {
            if ( setEntity.trainingDayId < 0 ) {
                setEntity.trainingDayId = trainingDayRepository.addTrainingDay(trainingDay = TrainingDayEntity(0, 0, DateTimeEntity(trainingDayDate,
                        ZonedDateTime.of( trainingDayDate, LocalTime.now(), ZoneId.systemDefault()))))
            }
            setRepository.addSet(setEntity)
        }
    }

    fun addTrainingDay (trainingDayEntity: TrainingDayEntity ) : Long {
        var trainingDayId = 0L
        CoroutineScope(Dispatchers.IO).launch {
            trainingDayId = trainingDayRepository.addTrainingDay(trainingDay = trainingDayEntity)
        }
        return trainingDayId

    }

}