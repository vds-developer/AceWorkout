package vds.developer.aceworkout

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vds.developer.aceworkout.models.TrainingFragmentViewModel

class TrainingPageViewPagerAdapter(val context: Context,
                                   val trainingDaySetsReps: TrainingFragmentViewModel.TrainingDaySetsReps,
                                   val setItemListener: TrainingDayRecycleView.TrainingSetItemListener,
                                   val repItemListener: RepsRecycleView.RepItemListener
                                            ) :
        RecyclerView.Adapter<TrainingPageViewPagerAdapter.TrainingPageViewHolder>() {

    private var hasData : Boolean = false;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingPageViewHolder {
        hasData = trainingDaySetsReps.reps.isNotEmpty()
        var trainingPageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_day_page, parent, false)
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

    }

    class TrainingPageViewHolder(itemView : View, hasData:Boolean) : RecyclerView.ViewHolder(itemView) {
        var trainingDayRecyclerView : RecyclerView = itemView.findViewById(R.id.trainingDayRecyclerView)
        var addSetBtn : FloatingActionButton = itemView.findViewById(R.id.addWorkout)
        var noDataText : TextView = itemView.findViewById(R.id.noData)
        init {
            if(hasData){
                trainingDayRecyclerView.visibility = View.VISIBLE
                addSetBtn.visibility = View.VISIBLE
                noDataText.visibility = View.GONE
            } else {
                trainingDayRecyclerView.visibility = View.GONE
                addSetBtn.visibility = View.VISIBLE
                noDataText.visibility = View.VISIBLE
            }

        }

    }

}