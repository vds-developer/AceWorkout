package vds.developer.aceworkout.RecyclerView


//import vds.developer.aceworkout.pages.trainingFragment.TrainingFragment.OnListFragmentInteractionListener;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vds.developer.aceworkout.R
import vds.developer.aceworkout.pages.trainingFragment.TrainingFragmentViewModel
import java.util.*


class TrainingRecycleView
//    private final OnListFragmentInteractionListener mListener;

(private val listItemLayout: Int, private val exerciseItems: ArrayList<TrainingFragmentViewModel>) : RecyclerView.Adapter<TrainingRecycleView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.exercise_item, parent, false)
        return ViewHolder(view)
    }

    // load data in each row element
    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
//        val item = holder.textView
//        item.text = exerciseItems[listPosition].name
//        item.setTextColor(Color.GREEN)
    }


    override fun getItemCount(): Int {
        return exerciseItems.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var item: TrainingFragmentViewModel? = null
        private val textView: TextView

        init {
            textView = view.findViewById<View>(R.id.row_item) as TextView
        }
    }
}
