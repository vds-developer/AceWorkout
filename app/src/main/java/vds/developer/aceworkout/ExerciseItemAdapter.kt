package vds.developer.aceworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseItemAdapter(exerciseItemModelList : List<SingleExerciseStatModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var exerciseItemModelList : List<SingleExerciseStatModel> = exerciseItemModelList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_set, parent, false)
        return RecycleExerciseItemViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return exerciseItemModelList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val exerciseStatModel = exerciseItemModelList[position]
        val viewHolder : RecycleExerciseItemViewHolder = holder as RecycleExerciseItemViewHolder
        viewHolder.repsText.text = exerciseStatModel.reps.toString()
        viewHolder.weightText.text = exerciseStatModel.weight.toString()
    }

    class RecycleExerciseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weightText : TextView = itemView.findViewById(R.id.weight)
        var repsText : TextView = itemView.findViewById(R.id.reps)
    }
}