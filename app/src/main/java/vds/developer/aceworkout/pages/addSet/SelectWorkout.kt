package vds.developer.aceworkout.pages.addSet

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.add_set_exercise_activity.*
import vds.developer.aceworkout.R
import vds.developer.aceworkout.db.entities.SetEntity
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

        selectWorkoutViewModel.exerciseEntity.observe(
                this, androidx.lifecycle.Observer {
            recyclerExerciseList.adapter = SelectWorkoutRecyclerView(it, trainingDayId, this)
        }
        )

    }

    override fun addSetClick(setEntity: SetEntity) {
        selectWorkoutViewModel.addSet(setEntity)
        Toast.makeText(applicationContext, "Added Set", Toast.LENGTH_LONG).show()
    }
}
