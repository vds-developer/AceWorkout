package vds.developer.aceworkout.pages.trainingFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import vds.developer.aceworkout.R
import vds.developer.aceworkout.db.entities.SetEntity


class TrainingDayRecycleView(
        var trainingDaySetsReps: TrainingFragmentViewModel.TrainingDaySetsReps,
        val setItemListener: TrainingSetItemListener,
        val repItemListener: RepsRecycleView.RepItemListener
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var sets = trainingDaySetsReps.setEntities

    fun updateData(trainingDaySetsReps: TrainingFragmentViewModel.TrainingDaySetsReps) {
        this.trainingDaySetsReps = trainingDaySetsReps
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_training_set, parent, false)
        return RecycleTrainingSetViewHolder(rootView)
    }


    override fun getItemCount(): Int {
        return sets!!.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val set = sets!![position]
        val viewHolder: RecycleTrainingSetViewHolder = holder as RecycleTrainingSetViewHolder
        viewHolder.setName.text = set.setName
        viewHolder.addSetButton.setOnClickListener {
            setItemListener.onAddTrainingSetButtonClick(set)
        }
        viewHolder.setName.setOnClickListener {
            setItemListener.showStats(set)

        }

        if ( viewHolder.setsRecyclerView.adapter == null ) {
            viewHolder.setsRecyclerView.adapter =
                    RepsRecycleView(trainingDaySetsReps.repEntities!!.filter { rep -> rep.setId == set.setId }, repItemListener)
        } else {
            (viewHolder.setsRecyclerView.adapter as RepsRecycleView).updateData(trainingDaySetsReps.repEntities!!.filter { rep -> rep.setId == set.setId } )
        }



    }

    inner class RecycleTrainingSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var setName: TextView = itemView.findViewById(R.id.training_name)
        var setsRecyclerView: RecyclerView = itemView.findViewById(R.id.trainingSetRecyclerView)
        var addSetButton: MaterialButton = itemView.findViewById(R.id.add_set_btn)

    }

    interface TrainingSetItemListener {
        fun onAddTrainingSetButtonClick(setEntity: SetEntity)
        fun showStats(setEntity: SetEntity)
    }

}