package vds.developer.aceworkout.addSet

import android.icu.util.LocaleData
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import vds.developer.aceworkout.R

import kotlinx.android.synthetic.main.add_set_body_part_activity.*
import kotlinx.android.synthetic.main.add_set_body_part_activity.topAppBar
import vds.developer.aceworkout.models.*
import java.time.LocalDate

class AddSetActivity : AppCompatActivity() {
    private lateinit var addSetActivityViewModel : AddSetActivityViewModel


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