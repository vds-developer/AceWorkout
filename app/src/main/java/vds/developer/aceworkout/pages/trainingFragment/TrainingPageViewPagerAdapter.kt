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
import vds.developer.aceworkout.pages.addSet.AddSetActivity

class TrainingPageViewPagerAdapter(val context: Context,
                                   private val trainingDaySetsReps: MutableList<TrainingFragmentViewModel.TrainingDaySetsReps>,
                                   private val setItemListener: TrainingDayRecycleView.TrainingSetItemListener,
                                   private val repItemListener: RepsRecycleView.RepItemListener
) :
        RecyclerView.Adapter<TrainingPageViewPagerAdapter.TrainingPageViewHolder>() {

    private var hasData: Boolean = false
    private lateinit var parent: ViewGroup
    private var currentPageIndex: Int = 0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingPageViewHolder {

        var trainingPageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_day_page, parent, false)
        this.parent = parent
        return TrainingPageViewHolder(trainingPageView, hasData)
    }

    override fun getItemCount(): Int {
        if (trainingDaySetsReps.size > 0) return Int.MAX_VALUE
        else return 0
    }

    override fun onBindViewHolder(holder: TrainingPageViewHolder, position: Int) {
//        if(position < currentPageIndex ) {
//
//        }else {
//
//        }
//        var trainingDay = training.value
//        var newPosition = position % trainingDaySetsReps.size
//        val currentPosition = position % trainingDaySetsReps.size
        hasData = trainingDaySetsReps.isNotEmpty() &&
                trainingDaySetsReps.size > position &&
                trainingDaySetsReps[position].repEntities != null
//        hasData = true
        if (hasData) {
            holder.trainingDayRecyclerView.visibility = View.VISIBLE
            holder.noDataText.visibility = View.GONE
            holder.trainingDayRecyclerView.adapter =
                    TrainingDayRecycleView(trainingDaySetsReps[position], setItemListener, repItemListener)
        } else {
            holder.trainingDayRecyclerView.visibility = View.GONE
            holder.noDataText.visibility = View.VISIBLE
        }
        holder.addWorkout.setOnClickListener {
            val intent = Intent(parent.context, AddSetActivity::class.java)
            intent.putExtra("trainingDayId", trainingDaySetsReps[position].trainingDayEntity.trainingDayId)
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