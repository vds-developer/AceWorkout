package vds.developer.aceworkout.db.repository

import android.app.Application
import android.os.Build
import vds.developer.aceworkout.db.TrainingDataBase
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.entities.RepEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.db.entities.TrainingDayEntity
import java.time.LocalDate

//@RequiresApi(Build.VERSION_CODES.O)
class TrainingDayRepository(val app: Application) {

    private val trainingDayDao = TrainingDataBase.getDataBase(app).trainingDayDao()
    private val setDao = TrainingDataBase.getDataBase(app).setDao()
    private val repDao = TrainingDataBase.getDataBase(app).repDao()
    private val exerciseDao = TrainingDataBase.getDataBase(app).exerciseDao()


//    lateinit var date : LocalDate;

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            date = LocalDate.now()
        }
    }

    suspend fun getTrainingDay(date: LocalDate): TrainingDayEntity {
        return trainingDayDao.getTrainingDayByDate(date)
    }

    suspend fun getAllTrainingDay() : List<TrainingDayEntity> {
        return trainingDayDao.getAllTrainingDay()
    }

    suspend fun getSetsByTrainingDayId(trainingId: Long): List<SetEntity> {
        return setDao.getSetByTrainingDayId(trainingId)
    }

    suspend fun getRepsBySetId(setId: Long): List<RepEntity> {
        return repDao.getAllRepBySet(setId)
    }

    suspend fun getExerciseById(exerciseId: Long): ExerciseEntity {
        return exerciseDao.getExerciseById(exerciseId)
    }

    suspend fun insertRep(repEntity: RepEntity) {
        repDao.addRep(repEntity)
    }

    suspend fun deleteRep(repEntity: RepEntity) {
        repDao.deleteRep(repEntity)
    }
}