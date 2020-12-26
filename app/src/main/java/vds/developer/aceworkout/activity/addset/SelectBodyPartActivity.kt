package vds.developer.aceworkout.activity.addset

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_add_set.*
import vds.developer.aceworkout.MainActivity
import vds.developer.aceworkout.R
import vds.developer.aceworkout.activity.addexercise.AddExercise
import vds.developer.aceworkout.activity.addset.adapter.SelectBodyPartRecyclerViewAdapter
import vds.developer.aceworkout.activity.addset.viewmodel.AddSetActivityViewModel
import java.time.LocalDate

class SelectBodyPartActivity : AppCompatActivity() {
    private lateinit var addSetActivityViewModel: AddSetActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_set)
        topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        var trainingDayId = intent.extras.get("trainingDayId") as Long
        var trainingDayDate = intent.extras.get("trainingDayDate") as LocalDate

        bodyPartRecyclerView.adapter = SelectBodyPartRecyclerViewAdapter(trainingDayId, trainingDayDate)
        topAppBar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.add -> {
                    val intent = Intent(this, AddExercise::class.java)
                    ContextCompat.startActivity(this, intent, null)
                    true
                }
                else -> false
            }
        }
    }
}