package vds.developer.aceworkout.data.database

import vds.developer.aceworkout.data.entities.*
import vds.developer.aceworkout.data.entities.Set
import java.time.LocalDate
import java.time.ZonedDateTime

class DataGenerator {

    companion object {
        fun getTrainingDay(): List<TrainingDay> {
            return listOf(
                    TrainingDay(1, 3, DateTime(LocalDate.now(), ZonedDateTime.now())),
                    TrainingDay(2, 3, DateTime(LocalDate.now().plusDays(1), ZonedDateTime.now().plusDays(1))),
                    TrainingDay(3, 3, DateTime(LocalDate.now().plusDays(2), ZonedDateTime.now().plusDays(2)))
            )
        }

        fun getExercise(): List<Exercise> {
            return listOf(
                    Exercise(1, "Bench", "Chest", true),
                    Exercise(2, "Squat", "Legs", true),
                    Exercise(3, "Pullup", "Back", true),
                    Exercise(4, "Crunch", "Abs", true),
                    Exercise(5, "Running", "Cardio", false)
            )
        }

        fun getSet(): List<Set> {
            return listOf(
                    Set(1, "Set 1", 1, 5, 2),
                    Set(2, "Set 2", 1, 1, 2),
                    Set(3, "Set 3", 1, 2, 2),
                    Set(4, "Set 1", 2, 3, 2),
                    Set(5, "Set 2", 2, 4, 2),
                    Set(6, "Set 3", 2, 1, 2),
                    Set(7, "Set 1", 3, 2, 2),
                    Set(8, "Set 2", 3, 3, 2),
                    Set(9, "Set 3", 3, 4, 2)
            )
        }

        fun getRep(): List<Rep> {
            return listOf(
                    Rep(1, 9, 1, 5.0, 5, 0),
                    Rep(2, 9, 1, 5.0, 5, 0),
                    Rep(3, 1, 2, 5.0, 5, 0),
                    Rep(4, 1, 2, 5.0, 5, 0),
                    Rep(5, 2, 3, 5.0, 5, 0),
                    Rep(6, 2, 3, 5.0, 5, 0),
                    Rep(7, 3, 1, 5.0, 5, 0),
                    Rep(8, 3, 1, 5.0, 5, 0),
                    Rep(9, 4, 1, 5.0, 5, 0),
                    Rep(10, 4, 1, 5.0, 5, 0),
                    Rep(11, 5, 1, 5.0, 5, 0),
                    Rep(12, 5, 2, 5.0, 5, 0),
                    Rep(13, 6, 2, 5.0, 5, 0),
                    Rep(14, 6, 2, 5.0, 5, 0),
                    Rep(15, 7, 3, 5.0, 5, 0),
                    Rep(16, 7, 3, 5.0, 5, 0),
                    Rep(17, 8, 4, 5.0, 5, 0),
                    Rep(18, 8, 4, 5.0, 5, 0)
            )
        }
    }
}