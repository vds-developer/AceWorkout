package vds.developer.aceworkout.activity.trainingday

import android.os.Build
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.view_pager_training_day.*
import vds.developer.aceworkout.dialog.BottomSheetStatsFragment
import vds.developer.aceworkout.R
import vds.developer.aceworkout.dialog.RepDialog
import vds.developer.aceworkout.activity.trainingday.adapter.RepsRecycleViewAdapter
import vds.developer.aceworkout.activity.trainingday.adapter.TrainingDayRecycleViewAdapter
import vds.developer.aceworkout.activity.trainingday.adapter.TrainingPageViewPagerAdapter
import vds.developer.aceworkout.activity.trainingday.viewmodel.TrainingFragmentViewModel
import vds.developer.aceworkout.db.entities.RepEntity
import vds.developer.aceworkout.db.entities.SetEntity
import vds.developer.aceworkout.models.ViewModelFactory
import vds.developer.aceworkout.models.ViewModelsEnum

@RequiresApi(Build.VERSION_CODES.O)
class TrainingFragment : Fragment(),
        TrainingDayRecycleViewAdapter.TrainingSetItemListener,
        RepsRecycleViewAdapter.RepItemListener {

    lateinit var trainingFragmentViewModel: TrainingFragmentViewModel
    val context by lazy { this }
    lateinit var trainingViewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_pager_training_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.trainingViewPager = view.findViewById(R.id.trainingViewPager)



        trainingFragmentViewModel = ViewModelProvider(
                this,
                ViewModelFactory(
                        application = requireActivity().application,
                        viewModelEnum = ViewModelsEnum.TrainingFragment))
                .get(TrainingFragmentViewModel::class.java)



        trainingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                trainingFragmentViewModel.trainingDayRepsSets.value.let {
                    if (it != null) {
                        if (position > it.size - 1 ) {
                            trainingFragmentViewModel.setDate(it.last().trainingDayEntity.dateTime.localDate.plusDays(position.toLong() - it.size + 1))
                        } else {
                            trainingFragmentViewModel.setDate(it[position].trainingDayEntity.dateTime.localDate)
                        }
                        dateText.text = trainingFragmentViewModel.currentDate.toString()
                    }
                     }

                Log.d("void", "The current date is " + trainingFragmentViewModel.currentDate)
            }
        })

        trainingFragmentViewModel.trainingDayRepsSets.let { data ->
            data.observe(
                    this, androidx.lifecycle.Observer {
                if (trainingViewPager.adapter == null ) {
                    trainingViewPager.adapter = TrainingPageViewPagerAdapter(
                            this.requireContext(),
                            trainingFragmentViewModel.trainingDayRepsSets.value !!,
                            trainingFragmentViewModel,
                            this,
                            this)
                } else {
                    (trainingViewPager.adapter as TrainingPageViewPagerAdapter).setData(it)
                }
            })
        }
    }


    override fun onAddTrainingRepButtonClick(setEntity: SetEntity) {
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
