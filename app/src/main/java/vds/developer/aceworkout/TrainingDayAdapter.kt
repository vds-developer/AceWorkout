package vds.developer.aceworkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import vds.developer.aceworkout.models.SetModel
import vds.developer.aceworkout.models.TrainingDayModel


class TrainingDayAdapter(private var trainingDayModel: TrainingDayModel, private var fragmentManager: FragmentManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var parent: ViewGroup

    private var setArrayList: MutableList<SetModel> = trainingDayModel.sets


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_set, parent, false)
        return RecycleTrainingSetViewHolder(rootView)
    }


    override fun getItemCount(): Int {
        return setArrayList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val setModel = setArrayList[position]
        val viewHolder: RecycleTrainingSetViewHolder = holder as RecycleTrainingSetViewHolder
        viewHolder.setName.text = setModel.name
        viewHolder.addSetButton.setOnClickListener {
            addSet(position, viewHolder, setModel)
        }
        viewHolder.setsRecyclerView.adapter = ExerciseItemAdapter(setModel.set)




    }


    private fun deleteSet(position: Int, viewHolder: RecycleTrainingSetViewHolder, set: SetModel) {
        trainingDayModel.deleteSet(position)
        viewHolder.setsRecyclerView.removeAllViews()
    }


    private fun addSet(position: Int, viewHolder: RecycleTrainingSetViewHolder, set: SetModel) {
        val addSetDialog = AddSetDialog(trainingDayModel.sets.get(position), viewHolder.setsRecyclerView.adapter as ExerciseItemAdapter)
        addSetDialog.show(fragmentManager, null)


//        trainingViewModel.addSet(position, SingleExerciseStatModel())
//        var view = LayoutInflater.from(context).inflate(R.layout.single_rep, null)
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
        var setName: TextView = itemView.findViewById(R.id.training_name)
        var setsRecyclerView: RecyclerView = itemView.findViewById(R.id.trainingSetRecyclerView)
        var addSetButton: MaterialButton = itemView.findViewById(R.id.add_set_btn)

    }
}