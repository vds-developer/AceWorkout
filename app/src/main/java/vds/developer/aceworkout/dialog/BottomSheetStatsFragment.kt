package vds.developer.aceworkout.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet_stat.*
import vds.developer.aceworkout.R
import java.util.concurrent.TimeUnit


// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    BottomSheetStatsFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class BottomSheetStatsFragment(private val exerciseId: Long) : BottomSheetDialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_stat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setData(10, 10f)
    }

    protected fun getRandom(range: Float, start: Float): Float {
        return (Math.random() * range).toFloat() + start
    }

    private fun setData(count: Int, range: Float) {

        // now in hours
        val now: Long = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis())
        val values: ArrayList<Entry> = ArrayList()

        // count = hours
        val to = now + count.toFloat()

        // increment by 1 hour
        var x = now.toFloat()
        while (x < to) {
            val y: Float = getRandom(range, 50f)
            values.add(Entry(x, y)) // add one entry per hour
            x++
        }

        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1")
        set1.axisDependency = AxisDependency.LEFT
        set1.color = ColorTemplate.getHoloBlue()
        set1.valueTextColor = ColorTemplate.getHoloBlue()
        set1.lineWidth = 1.5f
        set1.setDrawCircles(false)
        set1.setDrawValues(false)
        set1.fillAlpha = 65
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)

        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(9f)

        // set data
        lineChart.data = data
    }
}
