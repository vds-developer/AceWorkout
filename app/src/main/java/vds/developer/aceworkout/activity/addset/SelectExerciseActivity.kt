package vds.developer.aceworkout.activity.addset

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_select_exercise.*
import vds.developer.aceworkout.R
import vds.developer.aceworkout.activity.addset.adapter.SelectExerciseRecyclerViewAdapter
import vds.developer.aceworkout.activity.addset.viewmodel.SelectExerciseViewModel
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.models.ViewModelFactory
import vds.developer.aceworkout.models.ViewModelsEnum

class SelectExerciseActivity : AppCompatActivity(), SelectExerciseRecyclerViewAdapter.AddSetListener {
    private lateinit var selectWorkoutViewModel: SelectExerciseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_exercise)
        topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }


        var bodyPart = "all"
        bodyPart = intent.extras.get("bodyPart").toString()
        var trainingDayId = intent.extras.get("trainingDayId") as Long
        bodyPart.let { Log.i("DEBUG", it) }


        selectWorkoutViewModel = ViewModelProvider(this, ViewModelFactory(application = this.application, viewModelEnum = ViewModelsEnum.SelectWorkout))
                .get(SelectExerciseViewModel::class.java)

        selectWorkoutViewModel.updateFilter(bodyPart)

        selectWorkoutViewModel.exerciseEntity.observe(
                this, androidx.lifecycle.Observer {
            recyclerExerciseList.adapter = SelectExerciseRecyclerViewAdapter(it, trainingDayId, this)
        }
        )

    }

    override fun addSetClick(setEntity: SetEntity) {
        selectWorkoutViewModel.addSet(setEntity)
        Toast.makeText(applicationContext, "Added Set", Toast.LENGTH_LONG).show()
    }
}
