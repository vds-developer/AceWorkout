package vds.developer.aceworkout.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vds.developer.aceworkout.fragments.ReportFragment;
import vds.developer.aceworkout.fragments.SettingFragment;
import vds.developer.aceworkout.fragments.SummaryFragment;
import vds.developer.aceworkout.fragments.TrainingFragment;

public class BottomNavPagerAdapter extends FragmentPagerAdapter {

    public BottomNavPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int fragmentIndex) {
        switch (fragmentIndex) {
            case 0: return new ReportFragment();
            case 1: return new SummaryFragment();
            case 2: return new TrainingFragment();
            case 3: return new SettingFragment();
            default: return new SummaryFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //to be configured in the activity
        return null;
    }


}
