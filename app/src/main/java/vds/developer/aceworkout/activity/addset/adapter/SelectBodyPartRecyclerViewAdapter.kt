package vds.developer.aceworkout.activity.addset.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import vds.developer.aceworkout.R
import vds.developer.aceworkout.StringResources
import vds.developer.aceworkout.activity.addset.SelectExerciseActivity
import vds.developer.aceworkout.models.BodyPartEnum

class SelectBodyPartRecyclerViewAdapter(internal val trainingDayId: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val bodyParts: Array<BodyPartEnum> = BodyPartEnum.values()
    private lateinit var parent: ViewGroup


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_body_part, parent, false)
        this.parent = parent
        return BodyPartViewHolder(rootView)

    }

    override fun getItemCount(): Int {
        return bodyParts.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var description = ""
        var title = ""
        when (bodyParts[position]) {
            BodyPartEnum.Arms -> {
                description = StringResources.bodyPartDescriptionArm
                title = StringResources.bodyPartNameArm
            }
            BodyPartEnum.Back -> {
                description = StringResources.bodyPartDescriptionBack
                title = StringResources.bodyPartNameBack
            }
            BodyPartEnum.Legs -> {
                description = StringResources.bodyPartDescriptionLegs
                title = StringResources.bodyPartNameLegs
            }
        }
        holder as BodyPartViewHolder
        holder.let {
            it.description.text = description
            it.title.text = title
            it.card.setOnClickListener {
                val intent = Intent(parent.context, SelectExerciseActivity::class.java)
                intent.putExtra("bodyPart", bodyParts[position].name)
                intent.putExtra("trainingDayId", trainingDayId)
                startActivity(parent.context, intent, null)
            }
        }
    }

    inner class BodyPartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var card: MaterialCardView = itemView.findViewById(R.id.bodyPartCard)
        var image: ImageView = itemView.findViewById(R.id.bodyPartImage)
        var description: TextView = itemView.findViewById(R.id.bodyPartDescription)
        var title: TextView = itemView.findViewById(R.id.bodyPartTitle)

    }


}