package vds.developer.aceworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import vds.developer.aceworkout.models.TrainingModel
import vds.developer.aceworkout.models.TrainingViewModel


class TrainingViewAdapter(private var trainingViewModel: TrainingViewModel, private var fragmentManager: FragmentManager, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var recycleViewItem: View
    lateinit var parent: ViewGroup
    lateinit var recycleView: View
    lateinit var singleExerciseViewAdapterList: MutableList<ExerciseItemAdapter>

    var trainingModelArrayList: MutableList<TrainingModel> = trainingViewModel.trainingList.value!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_item, parent, false)
        recycleViewItem = rootView
        return RecycleTrainingSetViewHolder(rootView)
    }


    override fun getItemCount(): Int {
        return trainingModelArrayList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val trainingModel = trainingModelArrayList[position]
        val viewHolder: RecycleTrainingSetViewHolder = holder as RecycleTrainingSetViewHolder
        viewHolder.trainingName.text = trainingModel.name
        viewHolder.addSetButton.setOnClickListener {
            addSet(position, viewHolder, trainingModel)
        }
//        trainingModel.stats.
        viewHolder.trainingRecyclerView.adapter = ExerciseItemAdapter(trainingModel.stats)




    }


    private fun deleteSet(position: Int, viewHolder: RecycleTrainingSetViewHolder, trainingModel: TrainingModel) {
        trainingViewModel.deleteSet(position, SingleExerciseStatModel())
        viewHolder.trainingRecyclerView.removeAllViews()
    }


    private fun addSet(position: Int, viewHolder: RecycleTrainingSetViewHolder, trainingModel: TrainingModel) {
        val addSetDialog = AddSetDialog(trainingViewModel, position, viewHolder.trainingRecyclerView.adapter as ExerciseItemAdapter)
        addSetDialog.show(fragmentManager, null)
//        viewHolder.trainingRecyclerView.removeAllViews()
        var singleExerciseStatView: View?
//        for (singleExerciseStatModel in trainingModel.stats) {
//            singleExerciseStatView = LayoutInflater.from(recycleView.context).inflate(R.layout.single_set, null)
//            singleExerciseStatView!!.findViewById<TextView>(R.id.weight).text = singleExerciseStatModel.weight.toString()
//            singleExerciseStatView.findViewById<TextView>(R.id.reps).text = singleExerciseStatModel.reps.toString()
//            viewHolder.trainingLinearLayout.addView(singleExerciseStatView)
//        }


//        trainingViewModel.addSet(position, SingleExerciseStatModel())
//        var view = LayoutInflater.from(context).inflate(R.layout.single_set, null)
//        view.findViewById<TextView>(R.id.weight).text = "0.0 lbs"
//        view.findViewById<TextView>(R.id.reps).text = "0 reps"
////        view.setOnClickListener { view.setBackgroundColor(Color.RED) }
//        view.setOnTouchListener  { v, event ->
//            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
//                Log.d("TouchTest", "Touch down");
//                view.setBackgroundColor(Color.RED);
//            } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
//                Log.d("TouchTest", "Touch up");
//                view.setBackgroundColor(Color.TRANSPARENT);
//            }
//             true;
//        }
//        viewHolder.trainingLinearLayout.addView(view)
    }

    class RecycleTrainingSetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var trainingName: TextView = itemView.findViewById(R.id.training_name)
        var trainingRecyclerView: RecyclerView = itemView.findViewById(R.id.trainingSetRecyclerView)
        var addSetButton: MaterialButton = itemView.findViewById(R.id.add_set_btn)

    }
}