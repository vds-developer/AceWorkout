package vds.developer.aceworkout.activity.addset.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import vds.developer.aceworkout.MainActivity
import vds.developer.aceworkout.R
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.entities.SetEntity
import java.time.LocalDate

class SelectExerciseRecyclerViewAdapter(var exerciseEntities: List<ExerciseEntity>,
                                        internal var trainingDayId: Long,
                                        var addSetListener: AddSetListener ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var parent: ViewGroup


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_select_exercise, parent, false)
        this.parent = parent

        return WorkoutViewHolder(rootView)
    }

    fun updateExerciseList(exerciseEntities: List<ExerciseEntity>) {
        this.exerciseEntities = exerciseEntities
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return exerciseEntities.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as WorkoutViewHolder
        var exercise = exerciseEntities[position]
        holder.workoutText.let {
            it.text = exerciseEntities[position].name
            it.setOnClickListener {
                addSetListener.addSetClick(SetEntity(0L, exercise.name, trainingDayId, exercise.exerciseId, 0))
                var intent = Intent(parent.context, MainActivity::class.java)
                ContextCompat.startActivity(parent.context, intent, null)
            }
        }
    }

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutText: TextView = itemView.findViewById(R.id.exerciseItemText)
    }

    interface AddSetListener {
        fun addSetClick(setEntity: SetEntity)
    }


}