package vds.developer.aceworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import vds.developer.aceworkout.models.TrainingViewModel

class TrainingPageViewPagerAdapter(private var trainingViewModel: TrainingViewModel, private var fragmentManager: FragmentManager) : RecyclerView.Adapter<TrainingPageViewPagerAdapter.TrainingPageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingPageViewHolder {
        val trainingPageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.training_day_page, parent, false)
        return TrainingPageViewHolder(trainingPageView)
    }

    override fun getItemCount(): Int {
        return trainingViewModel.getTotalDays()
    }

    override fun onBindViewHolder(holder: TrainingPageViewHolder, position: Int) {
        holder.trainingDayRecyclerView.adapter =
                TrainingDayAdapter(trainingViewModel.getTrainingDay(position), fragmentManager)
    }

    class TrainingPageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var trainingDayRecyclerView : RecyclerView = itemView.findViewById(R.id.trainingDayRecyclerView)

    }
}