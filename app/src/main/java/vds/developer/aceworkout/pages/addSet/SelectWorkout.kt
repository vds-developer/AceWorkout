package vds.developer.aceworkout.pages.addSet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.add_set_exercise_activity.*
import kotlinx.android.synthetic.main.add_set_exercise_activity.topAppBar
import vds.developer.aceworkout.R
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.models.SelectWorkoutViewModel
import vds.developer.aceworkout.models.ViewModelFactory
import vds.developer.aceworkout.models.ViewModelsEnum

class SelectWorkout : AppCompatActivity(), SelectWorkoutRecyclerView.AddSetListener {
    private lateinit var selectWorkoutViewModel: SelectWorkoutViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_set_exercise_activity)
        topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }


        var bodyPart = "all"
        bodyPart = intent.extras.get("bodyPart").toString()
        var trainingDayId = intent.extras.get("trainingDayId") as Long
        bodyPart.let { Log.i("DEBUG", it) }


        selectWorkoutViewModel = ViewModelProvider(this, ViewModelFactory(application = this.application, viewModelEnum = ViewModelsEnum.SelectWorkout))
                .get(SelectWorkoutViewModel::class.java)

        selectWorkoutViewModel.updateFilter(bodyPart)

        selectWorkoutViewModel.exercise.observe(
                this, androidx.lifecycle.Observer {
            recyclerExerciseList.adapter = SelectWorkoutRecyclerView(it, trainingDayId, this)
        }
        )

    }

    override fun addSetClick(set: Set) {
        selectWorkoutViewModel.addSet(set)
        Toast.makeText(applicationContext, "Added Set", Toast.LENGTH_LONG).show()
    }
}
