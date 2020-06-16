package vds.developer.aceworkout.trainingFragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vds.developer.aceworkout.R
import vds.developer.aceworkout.data.entities.Rep


class RepsRecycleView(val reps : List<Rep>, val repItemListener: RepItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private var reps = trainingDaySetsReps.reps

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_rep, parent, false)
        return RecycleExerciseItemViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return this.reps.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rep = reps[position]
        val viewHolder : RecycleExerciseItemViewHolder = holder as RecycleExerciseItemViewHolder
        viewHolder.repsText.text = rep.reps.toString()
        viewHolder.weightText.text = rep.weight.toString()
        viewHolder.itemView.setOnClickListener { showMenu(viewHolder.itemView) }
        viewHolder.editButton.setOnClickListener { repItemListener.onEditRepButtonClick(rep) }
        viewHolder.deleteButton.setOnClickListener { repItemListener.onDeleteRepButtonClick(rep) }
    }

    private fun showMenu(view: View?) {
        val popup = PopupMenu(view?.context, view)
        popup.menuInflater.inflate(R.menu.single_reps_menu_option, popup.menu)
        popup.show()
    }

    interface RepItemListener {
        fun onEditRepButtonClick(rep : Rep)
        fun onDeleteRepButtonClick(rep : Rep)
    }

    class RecycleExerciseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weightText : TextView = itemView.findViewById(R.id.weight)
        var repsText : TextView = itemView.findViewById(R.id.reps)
        var editButton : ImageButton = itemView.findViewById(R.id.editButton)
        var deleteButton : ImageButton = itemView.findViewById(R.id.deleteButton)
    }
}