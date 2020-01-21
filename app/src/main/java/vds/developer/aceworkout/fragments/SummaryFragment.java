package vds.developer.aceworkout.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import vds.developer.aceworkout.Adapters.BottomNavPagerAdapter;
import vds.developer.aceworkout.R;

public class SummaryFragment extends Fragment {

    private RadarChart radarChart;
    private TextView numberDaysTrained;
    private BottomNavPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment,  container, false);
        radarChart = (RadarChart) view.findViewById(R.id.chart_summaryFragment_radarChart);
        numberDaysTrained = (TextView) view.findViewById(R.id.total_days_trained_number);
        numberDaysTrained.bringToFront();
        setUpRadarChart();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpRadarChart(){

        int backgroundColor = getResources().getColor(R.color.darkBackgroundColor);
        int textColor = getResources().getColor(R.color.darkTextColor);

        //configure radar chart
        radarChart.setBackgroundColor(backgroundColor);
        radarChart.getDescription().setEnabled(false);
        radarChart.setRotationEnabled(false);

        //color schemes
        radarChart.setWebLineWidth(1F);
        radarChart.setWebColor(textColor);
        radarChart.setWebColorInner(textColor);
        radarChart.setWebAlpha(100);
        radarChart.getLegend().setEnabled(false);


        //animation
        radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad);

        //define x axis
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(12F);
        xAxis.setYOffset(0);
        xAxis.setXOffset(0);
        xAxis.setValueFormatter(new ValueFormatter(){
            private String[] labels = new String[] {"Back", "Chest", "Arms", "Legs", "Shoulder"};
            @Override
            public String getFormattedValue(float value) {
                return labels[(int) value % labels.length];
            }
        });
        xAxis.setTextColor(textColor);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setTypeface(Typeface.DEFAULT);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);


        List<RadarEntry> radarSets = generateData(5);

        RadarDataSet set1 = new RadarDataSet(radarSets, "");
        set1.setDrawValues(true);
        set1.setColor(textColor);
        set1.setFillColor(Color.YELLOW);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarData data = new RadarData(set1);
        data.setValueTypeface(Typeface.DEFAULT);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(textColor);
        radarChart.setData(data);
        radarChart.invalidate();


    }

    private List<RadarEntry> generateData(int numLabels){
        float max = 100;
        List<RadarEntry> data = new ArrayList<RadarEntry>();
        for (int i = 0; i<numLabels; i++) {
            data.add( new RadarEntry((float) (Math.random() * max)));
        }
        return data;
    }
}
