package vds.developer.aceworkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.training_item.*
import vds.developer.aceworkout.models.TrainingViewModel

///**
// * A fragment representing a list of Items.
// * <p/>
// * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
// * interface.
// */
//    // TODO: Customize parameter argument names
//    private static final String ARG_COLUMN_COUNT = "column-count";
//    // TODO: Customize parameters
//    private int mColumnCount = 1;
//    private OnListFragmentInteractionListener mListener;

/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class TrainingFragment : Fragment() {

    private lateinit var trainingModel: TrainingViewModel
    val context by lazy { this }
    lateinit var training_fragment : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.training_fragment, container, false)
        trainingModel = ViewModelProvider(this).get(TrainingViewModel::class.java)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.training_fragment = getView()!!.findViewById(R.id.training_fragment)
//        var training_set_recycleView = this.training_fragment.findViewById<RecyclerView>(R.id.training_set_recycleView)

        trainingModel.trainingList.observe(this, androidx.lifecycle.Observer {
            training_fragment.adapter = TrainingViewAdapter(trainingModel)
        })
    }
}
