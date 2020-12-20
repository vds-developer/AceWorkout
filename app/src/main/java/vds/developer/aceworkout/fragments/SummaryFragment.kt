package vds.developer.aceworkout.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import vds.developer.aceworkout.Adapters.BottomNavPagerAdapter
import vds.developer.aceworkout.R
import java.util.*

class SummaryFragment : Fragment() {

    private var radarChart: RadarChart? = null
    private var numberDaysTrained: TextView? = null
    private val pagerAdapter: BottomNavPagerAdapter? = null
    private val viewPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        radarChart = view.findViewById<View>(R.id.chart_summaryFragment_radarChart) as RadarChart
        numberDaysTrained = view.findViewById<View>(R.id.total_days_trained_number) as TextView
        numberDaysTrained!!.bringToFront()
        setUpRadarChart()
        return view
    }

    private fun setUpRadarChart() {

        val backgroundColor = resources.getColor(R.color.darkBackgroundColor)
        val textColor = resources.getColor(R.color.darkTextColor)

        //configure radar chart
        radarChart!!.setBackgroundColor(backgroundColor)
        radarChart!!.description.isEnabled = false
        radarChart!!.isRotationEnabled = false

        //color schemes
        radarChart!!.webLineWidth = 1f
        radarChart!!.webColor = textColor
        radarChart!!.webColorInner = textColor
        radarChart!!.webAlpha = 100
        radarChart!!.legend.isEnabled = false


        //animation
        radarChart!!.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad)

        //define x axis
        val xAxis = radarChart!!.xAxis
        xAxis.textSize = 12f
        xAxis.yOffset = 0f
        xAxis.xOffset = 0f
        xAxis.valueFormatter = object : ValueFormatter() {
            private val labels = arrayOf("Back", "Chest", "Arms", "Legs", "Shoulder")
            override fun getFormattedValue(value: Float): String {
                return labels[value.toInt() % labels.size]
            }
        }
        xAxis.textColor = textColor

        val yAxis = radarChart!!.yAxis
        yAxis.typeface = Typeface.DEFAULT
        yAxis.setLabelCount(5, false)
        yAxis.textSize = 9f
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 80f
        yAxis.setDrawLabels(false)


        val radarSets = generateData(5)

        val set1 = RadarDataSet(radarSets, "")
        set1.setDrawValues(true)
        set1.color = textColor
        set1.fillColor = Color.YELLOW
        set1.setDrawFilled(true)
        set1.fillAlpha = 180
        set1.lineWidth = 2f
        set1.isDrawHighlightCircleEnabled = true
        set1.setDrawHighlightIndicators(false)

        val data = RadarData(set1)
        data.setValueTypeface(Typeface.DEFAULT)
        data.setValueTextSize(8f)
        data.setDrawValues(false)
        data.setValueTextColor(textColor)
        radarChart!!.data = data
        radarChart!!.invalidate()


    }

    private fun generateData(numLabels: Int): List<RadarEntry> {
        val max = 100f
        val data = ArrayList<RadarEntry>()
        for (i in 0 until numLabels) {
            data.add(RadarEntry((Math.random() * max).toFloat()))
        }
        return data
    }
}
