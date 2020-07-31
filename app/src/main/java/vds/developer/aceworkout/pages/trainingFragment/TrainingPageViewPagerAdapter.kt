package vds.developer.aceworkout.pages.trainingFragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vds.developer.aceworkout.R
import vds.developer.aceworkout.models.TrainingFragmentViewModel
import vds.developer.aceworkout.pages.addSet.AddSetActivity

class TrainingPageViewPagerAdapter(val context: Context,
                                   val trainingDaySetsReps: TrainingFragmentViewModel.TrainingDaySetsReps,
                                   val setItemListener: TrainingDayRecycleView.TrainingSetItemListener,
                                   val repItemListener: RepsRecycleView.RepItemListener
) :
        RecyclerView.Adapter<TrainingPageViewPagerAdapter.TrainingPageViewHolder>() {

    private var hasData: Boolean = false
    private lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingPageViewHolder {
        hasData = trainingDaySetsReps.reps.isNotEmpty()
        var trainingPageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_day_page, parent, false)
        this.parent = parent
        return TrainingPageViewHolder(trainingPageView, hasData)
    }

    override fun getItemCount(): Int {
        // todo change currently only 1 day
        return 1
    }

    override fun onBindViewHolder(holder: TrainingPageViewHolder, position: Int) {
//        var trainingDay = training.value
        if (hasData) {
            holder.trainingDayRecyclerView.adapter = TrainingDayRecycleView(trainingDaySetsReps, setItemListener, repItemListener)
        }
        holder.addWorkout.setOnClickListener {
            val intent = Intent(parent.context, AddSetActivity::class.java)
            intent.putExtra("trainingDayId", trainingDaySetsReps.trainingDay.trainingDayId)
            startActivity(parent.context, intent, null)
        }


    }

    class TrainingPageViewHolder(itemView: View, hasData: Boolean) : RecyclerView.ViewHolder(itemView) {
        var trainingDayRecyclerView: RecyclerView = itemView.findViewById(R.id.trainingDayRecyclerView)
        var addWorkout: FloatingActionButton = itemView.findViewById(R.id.addWorkout)
        var noDataText: TextView = itemView.findViewById(R.id.noData)

        init {
            if (hasData) {
                trainingDayRecyclerView.visibility = View.VISIBLE
                noDataText.visibility = View.GONE
            } else {
                trainingDayRecyclerView.visibility = View.GONE
                noDataText.visibility = View.VISIBLE
            }

        }

    }

}