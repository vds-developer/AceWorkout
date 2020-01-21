package vds.developer.aceworkout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vds.developer.aceworkout.Adapters.BottomNavPagerAdapter;
import vds.developer.aceworkout.fragments.SummaryFragment;

public class MainActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.main_fragment);
        BottomNavPagerAdapter pagerAdapter = new BottomNavPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout = findViewById(R.id.bottom_tab_slider);
        tabLayout.setupWithViewPager(viewPager);
        setUpTab();
    }


    private void setUpTab() {
        for (int index = 0; index<tabLayout.getTabCount(); index ++) {
            switch(index) {
                case 0 :
                    addTab(index, R.string.report, R.drawable.ic_launcher_foreground);
                    break;
                case 1 :
                    addTab(index, R.string.summary,R.drawable.ic_launcher_foreground);
                    break;
                case 2 :
                    addTab(index, R.string.training, R.drawable.ic_launcher_foreground);
                    break;
                case 3 :
                    addTab(index, R.string.setting, R.drawable.ic_launcher_foreground);
                    break;
                default : addTab(index, R.string.summary, R.drawable.ic_launcher_foreground);
            }
        }
        tabLayout.getTabAt(1).select();
    }

    private void addTab(int index, int titleID, int iconID ) {
        tabLayout.getTabAt(index).setText(titleID).setIcon(iconID);
    }


    private void switchFragments(Fragment fragment){
        if (fragmentManager == null) fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null)
                .commit();

    }
}
