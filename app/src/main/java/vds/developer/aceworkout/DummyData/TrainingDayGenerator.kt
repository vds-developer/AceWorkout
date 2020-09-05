package vds.developer.aceworkout.DummyData

import android.os.Build
import androidx.annotation.RequiresApi
import vds.developer.aceworkout.db.entities.*
import vds.developer.aceworkout.db.entities.SetEntity
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class TrainingDayGenerator {
    companion object {
        var random: Random = Random()
        var trainingDayEntities: List<TrainingDayEntity> = GenerateTrainingDay(1)
        var sets = GenerateSets(random.nextInt(5))
        var reps = GenerateReps(random.nextInt(5))
        var exercise = GenerateExercise(random.nextInt(5))


        fun GenerateTrainingDay(size: Int): List<TrainingDayEntity> {
            var dayEntities: MutableList<TrainingDayEntity> = mutableListOf()
            for (i in 1..size) {
                var dateTime = DateTimeEntity(LocalDate.now(), ZonedDateTime.now())
                dayEntities.add(TrainingDayEntity(i.toLong(), numberOfSets = 3, dateTime = dateTime))
            }
            return dayEntities.toList()
        }

        fun GenerateSets(size: Int): List<SetEntity> {
            var setEntities: MutableList<SetEntity> = mutableListOf()
            for (i in 1..size) {
                for (trainingDay in trainingDayEntities) {
                    setEntities.add(SetEntity(i.toLong(), "Name $i", trainingDay.trainingDayId, i.toLong(), 5))
                }
            }
            return setEntities.toList()
        }

        fun GenerateReps(size: Int): List<RepEntity> {
            var repEntities: MutableList<RepEntity> = mutableListOf()
            for (i in 1..size) {
                for (set in sets) {
                    repEntities.add(RepEntity(i.toLong(), set.setId, i.toLong(), 20.0, 10, 0))
                }
            }
            return repEntities.toList()
        }

        fun GenerateExercise(size: Int): List<ExerciseEntity> {
            var exerciseEntities: MutableList<ExerciseEntity> = mutableListOf()
            for (i in 1..size) {
                exerciseEntities.add(ExerciseEntity(i.toLong(), "Exercies $i", "Body part $1", true))
            }
            return exerciseEntities.toList()
        }

        fun Regenerate() {
            trainingDayEntities = GenerateTrainingDay(1)
            sets = GenerateSets(random.nextInt(5))
            reps = GenerateReps(random.nextInt(5))
            exercise = GenerateExercise(random.nextInt(5))
        }

    }
}