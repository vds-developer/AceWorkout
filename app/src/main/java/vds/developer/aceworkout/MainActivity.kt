package vds.developer.aceworkout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import vds.developer.aceworkout.pages.trainingFragment.TrainingFragment

class MainActivity : AppCompatActivity() {

    private var fragmentManager: FragmentManager? = null
    private var tabLayout: TabLayout? = null
    var isCalendar = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        switchFragments();
        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
            drawer.openDrawer(GravityCompat.START)
        }
    }


    private fun switchFragments() {
        if (fragmentManager == null) fragmentManager = supportFragmentManager
        fragmentManager!!.beginTransaction()
                .replace(R.id.main_fragment, TrainingFragment())
                .addToBackStack(null)
                .commit()

    }
}
