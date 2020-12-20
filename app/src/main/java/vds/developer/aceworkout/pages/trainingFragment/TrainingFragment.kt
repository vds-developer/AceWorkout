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
import vds.developer.aceworkout.R
import vds.developer.aceworkout.RepDialog
import vds.developer.aceworkout.db.entities.RepEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.models.ViewModelFactory
import vds.developer.aceworkout.models.ViewModelsEnum


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

        trainingFragmentViewModel.trainingDayRepsSets.let { data ->
            data.observe(
                    this, androidx.lifecycle.Observer {
                trainingViewPager.currentItem
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
    }

    override fun onAddTrainingSetButtonClick(setEntity: SetEntity) {
        showAddRep(setEntity)
        trainingFragmentViewModel.refresh()
    }

    private fun showAddRep(setEntity: SetEntity) {
        val addSetDialog = RepDialog(setEntity, trainingFragmentViewModel)
        fragmentManager?.let { addSetDialog.show(it, null) }
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
