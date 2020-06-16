package vds.developer.aceworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.models.TrainingFragmentViewModel


class TrainingDayRecycleView(
        val trainingDaySetsReps: TrainingFragmentViewModel.TrainingDaySetsReps,
        val setItemListener: TrainingSetItemListener,
        val repItemListener: RepsRecycleView.RepItemListener
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var sets = trainingDaySetsReps.sets

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_set, parent, false)
        return RecycleTrainingSetViewHolder(rootView)
    }


    override fun getItemCount(): Int {
        return sets.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val set = sets[position]
        val viewHolder: RecycleTrainingSetViewHolder = holder as RecycleTrainingSetViewHolder
        viewHolder.setName.text = set.setName
        viewHolder.addSetButton.setOnClickListener {
            setItemListener.onAddTrainingSetButtonClick(set)
        }
        viewHolder.setsRecyclerView.adapter = RepsRecycleView(trainingDaySetsReps.reps.filter { rep -> rep.setId == set.setId }, repItemListener)




    }

//
//    private fun deleteSet(position: Int, viewHolder: RecycleTrainingSetViewHolder, set: SetModel) {
//        trainingDayModel.deleteSet(position)
//        viewHolder.setsRecyclerView.removeAllViews()
//    }



    inner class RecycleTrainingSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var setName: TextView = itemView.findViewById(R.id.training_name)
        var setsRecyclerView: RecyclerView = itemView.findViewById(R.id.trainingSetRecyclerView)
        var addSetButton: MaterialButton = itemView.findViewById(R.id.add_set_btn)

    }

    interface TrainingSetItemListener {
        fun onAddTrainingSetButtonClick(set : Set)
    }
}