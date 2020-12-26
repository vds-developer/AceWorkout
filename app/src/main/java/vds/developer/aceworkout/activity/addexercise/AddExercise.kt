package vds.developer.aceworkout.activity.addexercise

import android.app.Application
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_exercise.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vds.developer.aceworkout.R
import vds.developer.aceworkout.db.dao.ExerciseDao
import vds.developer.aceworkout.db.entities.ExerciseEntity
import vds.developer.aceworkout.db.repository.ExerciseRepository
import vds.developer.aceworkout.models.BodyPartEnum

class AddExercise : AppCompatActivity() {

    private var exerciseRepository : ExerciseRepository = ExerciseRepository(Application())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        setSupportActionBar(findViewById(R.id.toolbar))

        val bodyParts = BodyPartEnum.values()
        val bodyPartAdapter = ArrayAdapter(this, R.layout.item_body_part_list, bodyParts)
        (exerciseBodyPart as? AutoCompleteTextView)?.setAdapter(bodyPartAdapter)



        cancelBtn.setOnClickListener {
            onBackPressed()
        }

        saveBtn.setOnClickListener{
            var validField = true
            exerciseNameLayout.error = null
            exerciseMenu.error = null
            if (exerciseNameText.text == null || exerciseNameText.text.toString().trim().isEmpty()) {
                exerciseNameLayout.error = "Exercise Name Cannot be empty"
                validField = false
            }

            if (exerciseBodyPart.text == null || exerciseBodyPart.text.toString().trim().isEmpty()) {
                exerciseMenu.error = "Exercise Body Part cannot be empty"
                validField = false
            }
            if (validField) {
                CoroutineScope(Dispatchers.IO).launch {
                    exerciseRepository.addExercise(ExerciseEntity(0,  exerciseNameText.text.toString(), exerciseBodyPart.text.toString(), isWeighted = isWeighted.isActivated))
                }
                onBackPressed()
            }
        }
    }
}