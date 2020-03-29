package vds.developer.aceworkout

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text
import vds.developer.aceworkout.models.TrainingModel
import vds.developer.aceworkout.models.TrainingViewModel
import java.security.AccessController.getContext
import java.util.*


class TrainingViewAdapter(trainingViewModel: TrainingViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var context: Context
    var trainingModelArrayList: MutableList<TrainingModel> = trainingViewModel.trainingList.value!!
    var trainingViewModel = trainingViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_item, parent, false)
        context = rootView.context

        return RecycleTrainingSetViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return trainingModelArrayList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val trainingModel = trainingModelArrayList[position]
        val viewHolder : RecycleTrainingSetViewHolder = holder as RecycleTrainingSetViewHolder
        viewHolder.trainingName.text = trainingModel.name
        viewHolder.btn_easy.setOnClickListener {
            trainingViewModel.addSet(position, SingleExerciseStatModel())
            var view = LayoutInflater.from(context).inflate(R.layout.single_set, null)
            view.findViewById<TextView>(R.id.weight).text = "0.0"
            view.findViewById<TextView>(R.id.reps).text = "0"
            viewHolder.trainingSet.addView(view)
        }
        for (singleItem : SingleExerciseStatModel in trainingModel.stats){
            var view = LayoutInflater.from(context).inflate(R.layout.single_set, null)
            view.findViewById<TextView>(R.id.weight).text = singleItem.weight.toString()
            view.findViewById<TextView>(R.id.reps).text = singleItem.reps.toString()
            viewHolder.trainingSet.addView(view)
        }
    }

    class RecycleTrainingSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var trainingName : TextView = itemView.findViewById(R.id.training_name)
        var trainingSet : LinearLayout = itemView.findViewById(R.id.training_set_linear)
        var btn_easy : MaterialButton = itemView.findViewById(R.id.btn_easy)
    }
}