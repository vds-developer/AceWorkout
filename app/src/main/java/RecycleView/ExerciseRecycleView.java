package RecycleView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ExerciseRecycleView<ExerciseModel> extends RecyclerView.Adapter<TrainingRecycleView.ViewHolder> {

    private ArrayList<ExerciseModel> exerciseModels;
    private ExerciseType exerciseType;

    public ExerciseRecycleView(ArrayList<ExerciseModel> exerciseModels, ExerciseType exerciseType) {
        this.exerciseModels = exerciseModels;
        this.exerciseType = exerciseType;
    }

    @NonNull
    @Override
    public TrainingRecycleView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.exercise_item, parent, false);
        switch(exerciseType) {
            case WEIGHTLIFTING: break;
            case ABS: break;
            case JUMPING: break;
            default: break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingRecycleView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
