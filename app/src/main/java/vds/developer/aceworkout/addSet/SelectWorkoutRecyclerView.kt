package vds.developer.aceworkout.addSet

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import vds.developer.aceworkout.MainActivity
import vds.developer.aceworkout.R
import vds.developer.aceworkout.data.entities.Exercise
import vds.developer.aceworkout.data.entities.Set

class SelectWorkoutRecyclerView(var exercises : List<Exercise>, internal var trainingDayId: Long, var addSetListener : AddSetListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var parent : ViewGroup;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context).
        inflate(R.layout.add_set_exercise_holder, parent, false)
        this.parent = parent

        return WorkoutViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as WorkoutViewHolder
        var exercise = exercises[position]
        holder.workoutText.let{
            it.text = exercises[position].name
            it.setOnClickListener {
                addSetListener.addSetClick(Set(0, exercise.name, trainingDayId, exercise.exerciseId, 0))
                var intent  = Intent(parent.context, MainActivity::class.java)
                ContextCompat.startActivity(parent.context, intent, null)
            }
        }
    }

    class WorkoutViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var workoutText : TextView = itemView.findViewById(R.id.exerciseItemText)
    }

    interface AddSetListener {
        fun addSetClick(set : Set)
    }

}