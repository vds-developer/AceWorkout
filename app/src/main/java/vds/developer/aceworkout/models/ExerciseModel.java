package vds.developer.aceworkout.models;

import RecycleView.ExerciseType;

public class ExerciseModel {
    private String name;
    private ExerciseType exerciseType;

    public ExerciseModel(String name, ExerciseType exerciseType) {
        this.name = name;
        this.exerciseType = exerciseType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ExerciseType getExerciseType() {return exerciseType;}


}
