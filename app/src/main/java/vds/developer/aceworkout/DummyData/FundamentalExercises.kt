package vds.developer.aceworkout.DummyData

import android.app.Application
import vds.developer.aceworkout.db.TrainingDataBase
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.models.BodyPartEnum

class FundamentalExercises {
    companion object {

        val fundamentalExercise: List<ExerciseEntity> = listOf(
                ExerciseEntity(0, "Squat", BodyPartEnum.Legs.name, true),
                ExerciseEntity(0, "Leg Press", BodyPartEnum.Legs.name, true),
                ExerciseEntity(0, "Dumbbell Lunge", BodyPartEnum.Legs.name, true),
                ExerciseEntity(0, "HexBar Deadlift", BodyPartEnum.Legs.name, true),
                ExerciseEntity(0, "Box Jump", BodyPartEnum.Legs.name, false),


                ExerciseEntity(0, "Dumbbell Row", BodyPartEnum.Back.name, true),
                ExerciseEntity(0, "Pull Up", BodyPartEnum.Back.name, true),
                ExerciseEntity(0, "Lat Pulldown", BodyPartEnum.Back.name, true),
                ExerciseEntity(0, "Barbell Row", BodyPartEnum.Back.name, true),
                ExerciseEntity(0, "Deadlift", BodyPartEnum.Back.name, true),

                ExerciseEntity(0, "Barbell Chest Press", BodyPartEnum.Chest.name, true),
                ExerciseEntity(0, "Dumbbell Chest Press", BodyPartEnum.Chest.name, true),
                ExerciseEntity(0, "Incline Dumbbell Flye", BodyPartEnum.Chest.name, true),
                ExerciseEntity(0, "Cable Cross", BodyPartEnum.Chest.name, true),
                ExerciseEntity(0, "Low Cable Cross", BodyPartEnum.Chest.name, true),
                ExerciseEntity(0, "Incline Dumbbell Press", BodyPartEnum.Chest.name, true),

                ExerciseEntity(0, "Dumbbell Curl", BodyPartEnum.Arms.name, true),
                ExerciseEntity(0, "Dip", BodyPartEnum.Arms.name, true),
                ExerciseEntity(0, "Barbell Curl", BodyPartEnum.Arms.name, true),
                ExerciseEntity(0, "Close Grip Chest Press", BodyPartEnum.Arms.name, true),
                ExerciseEntity(0, "Tricep Extension", BodyPartEnum.Arms.name, true),

                ExerciseEntity(0, "Dumbbell Shoulder Press", BodyPartEnum.Shoulders.name, true),
                ExerciseEntity(0, "Bent-Over Dumbbell Lateral Raises", BodyPartEnum.Shoulders.name, true),
                ExerciseEntity(0, "Dumbbell Lateral Raises", BodyPartEnum.Shoulders.name, true),
                ExerciseEntity(0, "Push Press", BodyPartEnum.Shoulders.name, true),

                ExerciseEntity(0, "Crunches", BodyPartEnum.Abdominal.name, true),
                ExerciseEntity(0, "Plank", BodyPartEnum.Abdominal.name, false),
                ExerciseEntity(0, "Mason Twist", BodyPartEnum.Abdominal.name, true),
                ExerciseEntity(0, "Bicycle Crunches", BodyPartEnum.Abdominal.name, false),
                ExerciseEntity(0, "Leg Raises", BodyPartEnum.Abdominal.name, false),
                ExerciseEntity(0, "Tuck and Crunch", BodyPartEnum.Abdominal.name, false),

                ExerciseEntity(0, "Running", BodyPartEnum.Others.name, true)
        )
    }


}