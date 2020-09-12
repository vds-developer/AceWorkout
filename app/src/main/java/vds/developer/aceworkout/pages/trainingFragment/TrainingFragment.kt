package vds.developer.aceworkout.pages.trainingFragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import vds.developer.aceworkout.R
import vds.developer.aceworkout.RepDialog
import vds.developer.aceworkout.db.entities.RepEntity
import vds.developer.aceworkout.db.entities.SetEntity
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

    //    private var currentPage : Int = Int.MAX_VALUE / 2
    private var currentPage: Int = 0

    private enum class PageState {
        Previous, CURRENT, NEXT
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.training_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.trainingViewPager = view.findViewById(R.id.trainingViewPager)

        trainingFragmentViewModel = ViewModelProvider(
                this,
                ViewModelFactory(
                        application = activity!!.application,
                        viewModelEnum = ViewModelsEnum.TrainingFragment))
                .get(TrainingFragmentViewModel::class.java)

//        swipeLayout.setDistanceToTriggerSync(300)
//
//
//
//        swipeLayout.setOnRefreshListener {
//            trainingFragmentViewModel.refresh()
//            swipeLayout.isRefreshing = false
//        }
//        trainingViewPager.setCurrentItem(currentPage, false)

//        trainingViewPager.registerOnPageChangeCallback(pageChangeCallback)
        trainingFragmentViewModel.trainingDayRepsSets.let { data ->
            data.observe(
                    this, androidx.lifecycle.Observer {
//                (trainingViewPager.adapter as TrainingPageViewPagerAdapter).setData(it)
                if (trainingViewPager.adapter == null ) {
                    trainingViewPager.adapter = TrainingPageViewPagerAdapter(
                            this.requireContext(),
                            trainingFragmentViewModel.trainingDayRepsSets.value!!,
                            trainingFragmentViewModel,
                            this,
                            this)
                } else {
                    (trainingViewPager.adapter as TrainingPageViewPagerAdapter).setData(it)
                }



            })
        }
//        Log.i("info","Postition" + currentPage.toString())

    }

    /*
     * ViewPager page change listener
     */
    var pageChangeCallback: OnPageChangeCallback = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
//            trainingViewPager.setCurrentItem(currentPage, false)
//            when(position) {
//                PageState.CURRENT.ordinal -> trainingViewPager.setCurrentItem(1, false)
//                PageState.NEXT.ordinal -> trainingFragmentViewModel.getNext()
//                PageState.Previous.ordinal ->  trainingFragmentViewModel.getPrev()
//            }

//            if (position > currentPage && position != 0){
//                trainingFragmentViewModel.getNext()
//                currentPage = position
//
//            } else if (position < currentPage && position != 0) {
//                trainingFragmentViewModel.getPrev()
//                currentPage = position
//            }
//
//            trainingViewPager.setCurrentItem(currentPage, false)
//            if (trainingFragmentViewModel.isUpdating) {
//                trainingFragmentViewModel.isUpdating = false
//                var currentIndex = 0
//                for(i in
//                trainingFragmentViewModel.trainingDayRepsSets.value!!.indices){
//                    if(trainingFragmentViewModel.trainingDayRepsSets.value!![i].trainingDayEntity.dateTime.localDate == trainingFragmentViewModel.date) {
//                        currentIndex = i
//                        break
//                    }
//                }
//            }
            if (!trainingFragmentViewModel.isUpdating)  trainingFragmentViewModel.currentPosition = position

            Log.i("info", "Postition" + position.toString())
            Toast.makeText(context.getContext(), position.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAddTrainingSetButtonClick(setEntity: SetEntity) {
        showAddRep(setEntity)
        trainingFragmentViewModel.refresh()
    }

    private fun showAddRep(setEntity: SetEntity) {
        val addSetDialog = RepDialog(setEntity, trainingFragmentViewModel)
        fragmentManager?.let { addSetDialog.show(it, null) }
//        trainingViewPager.adapter!!.notifyItemChanged(0)

    }

    override fun onEditRepButtonClick(repEntity: RepEntity) {
        val addSetDialog = RepDialog(repEntity, trainingFragmentViewModel)
        fragmentManager?.let { addSetDialog.show(it, null) }
    }

    override fun onDeleteRepButtonClick(repEntity: RepEntity) {
        trainingFragmentViewModel.deleteRep(repEntity)
    }

    override fun showStats(setEntity: SetEntity) {
        val bottomSheetStatsFragment = BottomSheetStatsFragment(exerciseId = setEntity.exerciseId)
        bottomSheetStatsFragment.show(fragmentManager!!, "test")
    }


}
