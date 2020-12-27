package vds.developer.aceworkout

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vds.developer.aceworkout.activity.trainingday.TrainingFragment
import vds.developer.aceworkout.db.repository.TrainingDayRepository
import java.time.DayOfWeek
import java.time.LocalDate

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
        initalize_week_btn()
    }

    private fun initalize_week_btn() {
        CoroutineScope(Dispatchers.Default).launch {
            var trainingDayRepository = TrainingDayRepository(application)
            var trainingDaysThisWeek = trainingDayRepository.getWeekTrainingDays(LocalDate.now())
            for (trainingDayEntity in trainingDaysThisWeek) {
                when(trainingDayEntity.dateTime.localDate.dayOfWeek) {
                    DayOfWeek.MONDAY -> {
                        btn_monday.setBackgroundColor(Color.GREEN)
                    }
                    DayOfWeek.TUESDAY -> {
                        btn_tuesday.setBackgroundColor(Color.GREEN)
                    }
                    DayOfWeek.WEDNESDAY -> {
                        btn_wednesday.setBackgroundColor(Color.GREEN)
                    }
                    DayOfWeek.THURSDAY -> {
                        btn_thursday.setBackgroundColor(Color.GREEN)
                    }
                    DayOfWeek.FRIDAY -> {
                        btn_friday.setBackgroundColor(Color.GREEN)
                    }
                    DayOfWeek.SATURDAY -> {
                        btn_saturday.setBackgroundColor(Color.GREEN)
                    }
                    DayOfWeek.SUNDAY -> {
                        btn_sunday.setBackgroundColor(Color.GREEN)
                    }
                }
            }
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
