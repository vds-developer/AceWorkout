package vds.developer.aceworkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_rep.*
import vds.developer.aceworkout.db.entities.RepEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.pages.trainingFragment.TrainingFragmentViewModel

class RepDialog() : DialogFragment() {

    var weight: Double = 0.0
    var reps: Int = 0
    lateinit var trainingFragmentViewModel: TrainingFragmentViewModel

    enum class mode { edit, add }

    lateinit var currentMode: mode
    var setEntity: SetEntity? = null
    var repEntity: RepEntity? = null

    constructor(setEntity: SetEntity, trainingFragmentViewModel: TrainingFragmentViewModel) : this() {
        currentMode = mode.add
        this.trainingFragmentViewModel = trainingFragmentViewModel
        this.setEntity = setEntity
    }

    constructor(repEntity: RepEntity, trainingFragmentViewModel: TrainingFragmentViewModel) : this() {
        currentMode = mode.edit
        this.trainingFragmentViewModel = trainingFragmentViewModel
        weight = repEntity.weight
        reps = repEntity.reps
        this.repEntity = repEntity

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_rep, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(WEIGHT, weight.toString())
        outState.putString(REPS, reps.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weightInputTxt.setText(weight.toString())
        repsInputTxt.setText(reps.toString())
        setSaveButtonClick()
        setCancelButtonClick()
    }

    private fun setCancelButtonClick() {
        cancelBtn.setOnClickListener {
            dismiss()
        }
    }


    private fun setSaveButtonClick() {
        saveBtn.setOnClickListener {
            // save to database and get data
            weight = if (weightInputTxt.text.isNullOrBlank()) 0.0 else weightInputTxt.text.toString().toDouble()
            reps = if (repsInputTxt.text.isNullOrBlank()) 0 else repsInputTxt.text.toString().toInt()
//            setsModel.addSet(SingleRep(weight!!, reps!!))
//            repsRecycleView.notifyItemInserted(setsModel.getSize()
            when (currentMode) {
                mode.add -> trainingFragmentViewModel.addRep(RepEntity(0, setEntity!!.setId, setEntity!!.exerciseId, weight, reps, 0))
                mode.edit -> trainingFragmentViewModel.editRep(RepEntity(repEntity!!.repId, repEntity!!.setId, repEntity!!.exerciseId, weight, reps, repEntity!!.time))
            }

            dismiss()
        }
    }
}