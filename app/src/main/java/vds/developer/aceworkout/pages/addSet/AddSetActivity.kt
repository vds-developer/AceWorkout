package vds.developer.aceworkout.pages.addSet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_set.*
import vds.developer.aceworkout.R

class AddSetActivity : AppCompatActivity() {
    private lateinit var addSetActivityViewModel: AddSetActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_set)
        topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        var trainingDayId = intent.extras.get("trainingDayId") as Long
        bodyPartRecyclerView.adapter = BodyPartRecyclerView(trainingDayId)
    }
}