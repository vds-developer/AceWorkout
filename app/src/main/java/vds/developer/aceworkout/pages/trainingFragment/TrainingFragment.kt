package vds.developer.aceworkout.pages.trainingFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import kotlinx.android.synthetic.main.training_view_pager.*
import vds.developer.aceworkout.R
import vds.developer.aceworkout.RepDialog
import vds.developer.aceworkout.data.entities.Rep
import vds.developer.aceworkout.data.entities.Set
import vds.developer.aceworkout.models.TrainingFragmentViewModel
import vds.developer.aceworkout.models.ViewModelFactory
import vds.developer.aceworkout.models.ViewModelsEnum


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
@RequiresApi(Build.VERSION_CODES.O)
class TrainingFragment : Fragment(),
        TrainingDayRecycleView.TrainingSetItemListener,
        RepsRecycleView.RepItemListener {

    lateinit var trainingFragmentViewModel: TrainingFragmentViewModel
    val context by lazy { this }
    lateinit var trainingViewPager: ViewPager2
//    private currentPage=0;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.training_view_pager, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.trainingViewPager = view.findViewById(R.id.trainingViewPager)
        trainingViewPager.registerOnPageChangeCallback(pageChangeCallback)
        trainingFragmentViewModel = ViewModelProvider(this, ViewModelFactory(application = activity!!.application, viewModelEnum = ViewModelsEnum.TrainingFragment))
                .get(TrainingFragmentViewModel::class.java)

        swipeLayout.setOnRefreshListener {
            trainingFragmentViewModel.refresh()
        }
//        val standardBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
//        standardBottomSheetBehavior.peekHeight = 300
//        standardBottomSheetBehavior.isHideable = true
//        fragmentManager

//        test.setOnClickListener {
////            if (standardBottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
////                standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
////            }else {
////                standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
////            }
////
//////            val view = layoutInflater.inflate(R.layout.fragment_bottom_sheet_stat, null)
//////            val dialog = BottomSheetDialog(activity!!.applicationContext)
//////            dialog.setContentView(view)
//////            dialog.show()
//            val BottomSheetStatsFragment = BottomSheetStatsFragment()
//            BottomSheetStatsFragment.show(fragmentManager!!, "test")
//        }
        trainingFragmentViewModel.trainingDayRepsSets.let { data ->
            data.observe(
                    this, androidx.lifecycle.Observer {
                trainingViewPager.adapter = TrainingPageViewPagerAdapter(this.requireContext(), it, this, this)
                swipeLayout.isRefreshing = false
            })
        }
    }

    /*
     * ViewPager page change listener
     */
    var pageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            dateText.text = trainingFragmentViewModel.date.toString()
        }
    }

    override fun onAddTrainingSetButtonClick(set: Set) {
        //todo
        showAddRep(set)
//        trainingFragmentViewModel.refresh()

    }

    private fun showAddRep(set: Set) {
        val addSetDialog = RepDialog(set, trainingFragmentViewModel)
        fragmentManager?.let { addSetDialog.show(it, null) }

    }

    override fun onEditRepButtonClick(rep: Rep) {
        val addSetDialog = RepDialog(rep, trainingFragmentViewModel)
        fragmentManager?.let { addSetDialog.show(it, null) }
    }

    override fun onDeleteRepButtonClick(rep: Rep) {
        trainingFragmentViewModel.deleteRep(rep)
    }

    override fun showStats(set: Set) {
        val BottomSheetStatsFragment = BottomSheetStatsFragment(exerciseId = set.exerciseId)
        BottomSheetStatsFragment.show(fragmentManager!!, "test")
    }


}
