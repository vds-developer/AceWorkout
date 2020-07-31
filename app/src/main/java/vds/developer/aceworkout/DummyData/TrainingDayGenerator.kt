package vds.developer.aceworkout.DummyData

import android.os.Build
import androidx.annotation.RequiresApi
import vds.developer.aceworkout.data.entities.*
import vds.developer.aceworkout.data.entities.Set
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class TrainingDayGenerator {
    companion object {
        var random: Random = Random()
        var trainingDays: List<TrainingDay> = GenerateTrainingDay(1)
        var sets = GenerateSets(random.nextInt(5))
        var reps = GenerateReps(random.nextInt(5))
        var exercise = GenerateExercise(random.nextInt(5))


        fun GenerateTrainingDay(size: Int): List<TrainingDay> {
            var days: MutableList<TrainingDay> = mutableListOf()
            for (i in 1..size) {
                var dateTime = DateTime(LocalDate.now(), ZonedDateTime.now())
                days.add(TrainingDay(i.toLong(), numberOfSets = 3, dateTime = dateTime))
            }
            return days.toList()
        }

        fun GenerateSets(size: Int): List<Set> {
            var sets: MutableList<Set> = mutableListOf()
            for (i in 1..size) {
                for (trainingDay in trainingDays) {
                    sets.add(Set(i.toLong(), "Name $i", trainingDay.trainingDayId, i.toLong(), 5))
                }
            }
            return sets.toList()
        }

        fun GenerateReps(size: Int): List<Rep> {
            var reps: MutableList<Rep> = mutableListOf()
            for (i in 1..size) {
                for (set in sets) {
                    reps.add(Rep(i.toLong(), set.setId, i.toLong(), 20.0, 10, 0))
                }
            }
            return reps.toList()
        }

        fun GenerateExercise(size: Int): List<Exercise> {
            var exercises: MutableList<Exercise> = mutableListOf()
            for (i in 1..size) {
                exercises.add(Exercise(i.toLong(), "Exercies $i", "Body part $1", true))
            }
            return exercises.toList()
        }

        fun Regenerate() {
            trainingDays = GenerateTrainingDay(1)
            sets = GenerateSets(random.nextInt(5))
            reps = GenerateReps(random.nextInt(5))
            exercise = GenerateExercise(random.nextInt(5))
        }

    }
}