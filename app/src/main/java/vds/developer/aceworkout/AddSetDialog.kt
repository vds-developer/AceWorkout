package vds.developer.aceworkout

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_set_template.*
import vds.developer.aceworkout.models.SetModel
import vds.developer.aceworkout.models.SingleRep

class AddSetDialog(internal var setsModel: SetModel, private var exerciseItemAdapter: ExerciseItemAdapter) : DialogFragment() {

    interface AddSetDialogListener {
        fun onFinishAddSetDialog(inputText: String?)
    }

    var weight : Double? = 0.0
    var reps : Int? = 0

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
            setsModel.addSet(SingleRep(weight!!, reps!!))
                exerciseItemAdapter.notifyItemInserted(setsModel.getSize())
            dismiss()
        }
    }
}