package vds.developer.aceworkout.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import vds.developer.aceworkout.fragments.ReportFragment
import vds.developer.aceworkout.fragments.SettingFragment
import vds.developer.aceworkout.fragments.SummaryFragment
import vds.developer.aceworkout.trainingFragment.TrainingFragment

class BottomNavPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(fragmentIndex: Int): Fragment {
        when (fragmentIndex) {
            0 -> return ReportFragment()
            1 -> return SummaryFragment()
            2 -> return TrainingFragment()
            3 -> return SettingFragment()
            else -> return SummaryFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //to be configured in the activity
        return null
    }


}
