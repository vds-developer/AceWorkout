package vds.developer.aceworkout.activity.trainingday.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import vds.developer.aceworkout.R
import vds.developer.aceworkout.activity.addset.SelectBodyPartActivity
import vds.developer.aceworkout.activity.trainingday.viewmodel.TrainingFragmentViewModel
import vds.developer.aceworkout.db.entities.DateTimeEntity
import vds.developer.aceworkout.db.entities.TrainingDayEntity
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

class TrainingPageViewPagerAdapter(val context: Context,
                                   private var trainingDaySetsReps: MutableList<TrainingFragmentViewModel.TrainingDaySetsReps>,
                                   private val trainingFragmentViewModel: TrainingFragmentViewModel,
                                   private val setItemListener: TrainingDayRecycleViewAdapter.TrainingSetItemListener,
                                   private val repItemListener: RepsRecycleViewAdapter.RepItemListener
) :
        RecyclerView.Adapter<TrainingPageViewPagerAdapter.TrainingPageViewHolder>() {

    private var hasData: Boolean = false
    private lateinit var parent: ViewGroup

    fun setData(trainingDaySetsReps: MutableList<TrainingFragmentViewModel.TrainingDaySetsReps>) {
        this.trainingDaySetsReps = trainingDaySetsReps
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingPageViewHolder {

        var trainingPageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_training_day, parent, false)
        this.parent = parent
        return TrainingPageViewHolder(trainingPageView, hasData)
    }

    override fun getItemCount(): Int {
        return if (trainingDaySetsReps.size >= 0) Int.MAX_VALUE
        else 0
    }

    override fun onBindViewHolder(holder: TrainingPageViewHolder, position: Int) {
        var model : TrainingFragmentViewModel.TrainingDaySetsReps? = null
        model = when {
            position == 0 -> {
                null
            }
            trainingFragmentViewModel.lastPosition < position -> {
                trainingFragmentViewModel.getTrainingDaySetsRepsByDate(trainingFragmentViewModel.currentDate.plusDays(
                        (position - trainingFragmentViewModel.lastPosition).toLong()))
            }
            trainingFragmentViewModel.lastPosition > position -> {
                trainingFragmentViewModel.getTrainingDaySetsRepsByDate(trainingFragmentViewModel.currentDate.minusDays(
                (trainingFragmentViewModel.lastPosition - position).toLong()
                ))
            }
            else -> {
                trainingFragmentViewModel.getTrainingDaySetsRepsByDate(trainingFragmentViewModel.currentDate)
            }
        }
        hasData = model != null && model.repEntities != null
        if (hasData) {
            model = model!!
            holder.trainingDayRecyclerView.visibility = View.VISIBLE
            holder.noDataText.visibility = View.GONE
               if (holder.trainingDayRecyclerView.adapter == null ) {
                   holder.trainingDayRecyclerView.adapter =
                           TrainingDayRecycleViewAdapter(model, setItemListener, repItemListener)
               } else {
                   (holder.trainingDayRecyclerView.adapter as TrainingDayRecycleViewAdapter).updateData(model)
               }

        } else {
            holder.trainingDayRecyclerView.visibility = View.GONE
            holder.noDataText.visibility = View.VISIBLE
        }

        holder.addWorkout.setOnClickListener {
            val intent = Intent(parent.context, SelectBodyPartActivity::class.java)
            if (model == null) {
                intent.putExtra("trainingDayId", -1L )
            } else {
                intent.putExtra("trainingDayId", model.trainingDayEntity.trainingDayId)
            }

            intent.putExtra("trainingDayDate", trainingFragmentViewModel.currentDate )
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