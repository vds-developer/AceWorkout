package vds.developer.aceworkout.addSet

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text
import vds.developer.aceworkout.R
import vds.developer.aceworkout.StringResources
import vds.developer.aceworkout.models.BodyPartEnum

class BodyPartRecyclerView: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val bodyParts:  Array<BodyPartEnum> = BodyPartEnum.values();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_set_body_part_holder, parent, false)
        return BodyPartViewHolder(rootView)
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return bodyParts.size
    }

    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int) {
        var description=""
        var title =""
        when(bodyParts[position]) {
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
        }
    }

    inner class BodyPartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.bodyPartImage)
        var description: TextView = itemView.findViewById(R.id.bodyPartDescription)
        var title: TextView = itemView.findViewById(R.id.bodyPartTitle)

    }


}