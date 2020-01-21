package RecycleView;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vds.developer.aceworkout.R;
//import vds.developer.aceworkout.fragments.TrainingFragment.OnListFragmentInteractionListener;

import vds.developer.aceworkout.models.ExerciseModel;

import java.util.ArrayList;


public class TrainingRecycleView extends RecyclerView.Adapter<TrainingRecycleView.ViewHolder> {

    private  ArrayList<ExerciseModel> exerciseItems;
    private int listItemLayout;
//    private final OnListFragmentInteractionListener mListener;

    public TrainingRecycleView(int listItemLayout, ArrayList<ExerciseModel> items) {
        exerciseItems = items;
        this.listItemLayout = listItemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item, parent, false);
        return new ViewHolder(view);
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.textView;
        item.setText(exerciseItems.get(listPosition).getName());
        item.setTextColor(Color.GREEN);
    }


    @Override
    public int getItemCount() {
        return exerciseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ExerciseModel item;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.row_item);
        }
    }
}
