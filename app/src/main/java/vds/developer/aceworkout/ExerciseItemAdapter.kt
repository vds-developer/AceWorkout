package vds.developer.aceworkout


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vds.developer.aceworkout.models.SingleRep


class ExerciseItemAdapter(singleRepItemModelList : List<SingleRep>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var singleRepItemModelList : List<SingleRep> = singleRepItemModelList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_rep, parent, false)
        return RecycleExerciseItemViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return this.singleRepItemModelList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val exerciseStatModel = singleRepItemModelList[position]
        val viewHolder : RecycleExerciseItemViewHolder = holder as RecycleExerciseItemViewHolder
        viewHolder.repsText.text = exerciseStatModel.reps.toString()
        viewHolder.weightText.text = exerciseStatModel.weight.toString()
        viewHolder.itemView.setOnClickListener { showMenu(viewHolder.itemView) }
    }

    private fun showMenu(view: View?) {
        val popup = PopupMenu(view?.context, view)
        popup.menuInflater.inflate(R.menu.single_reps_menu_option, popup.menu)
        popup.show()
    }

    class RecycleExerciseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weightText : TextView = itemView.findViewById(R.id.weight)
        var repsText : TextView = itemView.findViewById(R.id.reps)
    }
}