package vds.developer.aceworkout

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_set_template.*
import vds.developer.aceworkout.data.entities.Rep
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.models.SetModel
import vds.developer.aceworkout.models.SingleRep
import vds.developer.aceworkout.models.TrainingFragmentViewModel

class RepDialog( ) : DialogFragment() {

    var weight : Double = 0.0
    var reps : Int = 0
    lateinit var trainingFragmentViewModel: TrainingFragmentViewModel
    enum class mode  {edit, add}
    lateinit var currentMode : mode
    var set : Set? = null
    var rep : Rep? = null

    constructor(set: Set, trainingFragmentViewModel: TrainingFragmentViewModel) : this() {
        currentMode = mode.add
        this.trainingFragmentViewModel = trainingFragmentViewModel
        this.set = set
    }

    constructor( rep: Rep, trainingFragmentViewModel: TrainingFragmentViewModel) : this() {
        currentMode = mode.edit
        this.trainingFragmentViewModel = trainingFragmentViewModel
        weight = rep.weight
        reps = rep.reps
        this.rep = rep

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        weight = savedInstanceState?.getDouble(WEIGHT)
//        reps = savedInstanceState?.getInt(REPS)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_set_template, container, false)
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
            when(currentMode) {
                mode.add ->  trainingFragmentViewModel.addRep(Rep(0, set!!.setId, set!!.exerciseId, weight, reps, 0))
                mode.edit -> trainingFragmentViewModel.editRep(Rep(rep!!.repId, rep!!.setId, rep!!.exerciseId, weight, reps, rep!!.time))
            }

            dismiss()
        }
    }
}