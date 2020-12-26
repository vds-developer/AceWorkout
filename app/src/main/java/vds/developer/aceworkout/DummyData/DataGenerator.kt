package vds.developer.aceworkout.DummyData

import vds.developer.aceworkout.db.entities.*
import vds.developer.aceworkout.db.entities.SetEntity
import java.time.LocalDate
import java.time.ZonedDateTime

class DataGenerator {

    companion object {
        fun getTrainingDay(): List<TrainingDayEntity> {
            return listOf(
                    TrainingDayEntity(1, 3, DateTimeEntity(LocalDate.now(), ZonedDateTime.now())),
                    TrainingDayEntity(2, 3, DateTimeEntity(LocalDate.now().plusDays(1), ZonedDateTime.now().minusDays(1))),
                    TrainingDayEntity(3, 3, DateTimeEntity(LocalDate.now().plusDays(2), ZonedDateTime.now().minusDays(2)))
            )
        }

        fun getExercise(): List<ExerciseEntity> {
            return listOf(
                    ExerciseEntity(1, "Bench", "Chest", true),
                    ExerciseEntity(2, "Squat", "Legs", true),
                    ExerciseEntity(3, "Pullup", "Back", true),
                    ExerciseEntity(4, "Crunch", "Abs", true),
                    ExerciseEntity(5, "Running", "Cardio", false)
            )
        }

        fun getSet(): List<SetEntity> {
            return listOf(
                    SetEntity(1, "Set 1", 1, 5, 2),
                    SetEntity(2, "Set 2", 1, 1, 2),
                    SetEntity(3, "Set 3", 1, 2, 2),
                    SetEntity(4, "Set 4", 2, 3, 2),
                    SetEntity(5, "Set 5", 2, 4, 2),
                    SetEntity(6, "Set 6", 2, 1, 2),
                    SetEntity(7, "Set 7", 3, 2, 2),
                    SetEntity(8, "Set 8", 3, 3, 2),
                    SetEntity(9, "Set 9", 3, 4, 2)
            )
        }

        fun getRep(): List<RepEntity> {
            return listOf(
                    RepEntity(1, 9, 1, 5.0, 5, 0),
                    RepEntity(2, 9, 1, 5.0, 5, 0),
                    RepEntity(3, 1, 2, 5.0, 5, 0),
                    RepEntity(4, 1, 2, 5.0, 5, 0),
                    RepEntity(5, 2, 3, 5.0, 5, 0),
                    RepEntity(6, 2, 3, 5.0, 5, 0),
                    RepEntity(7, 3, 1, 5.0, 5, 0),
                    RepEntity(8, 3, 1, 5.0, 5, 0),
                    RepEntity(9, 4, 1, 5.0, 5, 0),
                    RepEntity(10, 4, 1, 5.0, 5, 0),
                    RepEntity(11, 5, 1, 5.0, 5, 0),
                    RepEntity(12, 5, 2, 5.0, 5, 0),
                    RepEntity(13, 6, 2, 5.0, 5, 0),
                    RepEntity(14, 6, 2, 5.0, 5, 0),
                    RepEntity(15, 7, 3, 5.0, 5, 0),
                    RepEntity(16, 7, 3, 5.0, 5, 0),
                    RepEntity(17, 8, 4, 5.0, 5, 0),
                    RepEntity(18, 8, 4, 5.0, 5, 0)
            )
        }
    }
}