package vds.developer.aceworkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import kotlinx.android.synthetic.main.training_view_pager.*
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



    private lateinit var trainingViewModel: TrainingViewModel
    val context by lazy { this }
    lateinit var trainingViewPager : ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.training_view_pager, container, false)
        trainingViewModel = ViewModelProvider(this).get(TrainingViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.trainingViewPager = view.findViewById(R.id.trainingViewPager)
        trainingViewPager.registerOnPageChangeCallback(pageChangeCallback)
//        var training_set_recycleView = this.training_day_page.findViewById<RecyclerView>(R.id.training_set_recycleView)
//        training_day_page.adapter = TrainingViewAdapter(trainingModel, fragmentManager!!, context.requireContext())
        trainingViewModel.trainingList.observe(this, androidx.lifecycle.Observer {
            trainingViewPager.adapter = TrainingPageViewPagerAdapter(trainingViewModel, fragmentManager!!)
        })
    }

    /*
     * ViewPager page change listener
     */
    var pageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            dateText.text = trainingViewModel.getDate(position).toString() + "$position";
        }
    }


}
