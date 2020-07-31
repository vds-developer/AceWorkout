package vds.developer.aceworkout.pages.addSet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_set_body_part_activity.*
import vds.developer.aceworkout.R
import vds.developer.aceworkout.models.AddSetActivityViewModel

class AddSetActivity : AppCompatActivity() {
    private lateinit var addSetActivityViewModel: AddSetActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_set_body_part_activity)
//        addSetActivityViewModel = ViewModelProvider(this, ViewModelFactory(application = this.application, viewModelEnum = ViewModelsEnum.AddSetActivity))
//                .get(AddSetActivityViewModel::class.java)
        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
            onBackPressed()
        }

        var trainingDayId = intent.extras.get("trainingDayId") as Long
        bodyPartRecyclerView.adapter = BodyPartRecyclerView(trainingDayId)

//        addSetActivityViewModel.allExercise.let {
//            it.observe(this, androidx.lifecycle.Observer {
//
//            })
//        }


    }
}