package vds.developer.aceworkout

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import vds.developer.aceworkout.Adapters.BottomNavPagerAdapter

class MainActivity : AppCompatActivity() {

    private var fragmentManager: FragmentManager? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        switchFragments(main_fragment);
    }



    private fun switchFragments(fragment: Fragment) {
        if (fragmentManager == null) fragmentManager = supportFragmentManager
        fragmentManager!!.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null)
                .commit()

    }
}
