package vds.developer.aceworkout.activity.addset

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.activity_select_exercise.*
import vds.developer.aceworkout.R
import vds.developer.aceworkout.activity.addset.adapter.SelectExerciseRecyclerViewAdapter
import vds.developer.aceworkout.activity.addset.viewmodel.SelectExerciseViewModel
import vds.developer.aceworkout.activity.trainingday.adapter.TrainingPageViewPagerAdapter
import vds.developer.aceworkout.db.entities.DateTimeEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.db.entities.TrainingDayEntity
import vds.developer.aceworkout.models.ViewModelFactory
import vds.developer.aceworkout.models.ViewModelsEnum
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

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
                this, Observer {
            if (recyclerExerciseList.adapter == null ) {
                recyclerExerciseList.adapter = SelectExerciseRecyclerViewAdapter(it, trainingDayId, this)
            } else {
                (recyclerExerciseList.adapter as SelectExerciseRecyclerViewAdapter).updateExerciseList(it)
            } }
        )

    }

    override fun addSetClick(setEntity: SetEntity) {
        selectWorkoutViewModel.addSetOrAddTrainingDayAndSet(setEntity, intent.extras.get("trainingDayDate") as LocalDate)
        Toast.makeText(applicationContext, "Added Set", Toast.LENGTH_LONG).show()
    }
}
