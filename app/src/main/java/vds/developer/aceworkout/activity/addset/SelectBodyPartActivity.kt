package vds.developer.aceworkout.activity.addset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_set.*
import vds.developer.aceworkout.R
import vds.developer.aceworkout.activity.addset.adapter.SelectBodyPartRecyclerViewAdapter
import vds.developer.aceworkout.activity.addset.viewmodel.AddSetActivityViewModel

class SelectBodyPartActivity : AppCompatActivity() {
    private lateinit var addSetActivityViewModel: AddSetActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_set)
        topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        var trainingDayId = intent.extras.get("trainingDayId") as Long
        bodyPartRecyclerView.adapter = SelectBodyPartRecyclerViewAdapter(trainingDayId)
    }
}