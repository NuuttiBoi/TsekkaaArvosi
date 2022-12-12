package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;


public class SykeGraafiActivity extends AppCompatActivity {

    private static final String TAG = "TestiActivity";

    private LineChart mChart;
    private LineGraphSeries<DataPoint> lineSeries;
    private PointsGraphSeries<DataPoint> pointSeries;
    private MittausViewModel mittausViewModel;
    private TextView yksikkoSykeTextView;
    private DataPoint[] dataPoints;
    private Button backButton;
    private double mittauksetKaikki, mittauksetKeskiarvo;
    private SharedPreferences sharedPref;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syke_graafi);

        mChart = (LineChart) findViewById(R.id.linechart);
        this.backButton = findViewById(R.id.backButton);
        this.yksikkoSykeTextView = findViewById(R.id.yksikkoSykeTextView);
        //mChart.setOnChartGestureListener(TestiActivity.this);
        //mChart.setOnChartValueSelectedListener(TestiActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        LimitLine upper_limit = new LimitLine(100, "Liian korkea");
        upper_limit.setLineWidth(3f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);
        upper_limit.setTextColor(Color.RED);


        LimitLine lower_limit = new LimitLine(60, "Liian matala");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f,10f,0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(15f);
        lower_limit.setTextColor(Color.RED);




        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);

        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);


        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
        mittausViewModel.haeYpApSykeMittaukset().observe(this, mittaukset -> {

            Long aikaArray[] = new Long[mittaukset.size()];
            String pvm[]=new String[mittaukset.size()];

            ArrayList<Entry> yValues = new ArrayList<>();
            ArrayList xValues = new ArrayList<>();
            for(int i = 0; i < mittaukset.size(); i++){
                pvm[i] = mittaukset.get(i).getAjankohta();
                xValues.add(pvm[i]);
            }

            for (int i = 0; i < mittaukset.size(); i++) {
                for (int j = 0; j < mittaukset.size(); j++) {
                    aikaArray[j] = mittaukset.get(j).getAika();

                }
                Arrays.sort(aikaArray);

                yValues.add(new Entry(i, mittaukset.get(i).getSyke().floatValue()));
                mittauksetKaikki = mittauksetKaikki + mittaukset.get(i).getSyke();
            }

            mittauksetKeskiarvo = (mittauksetKaikki / mittaukset.size());


            LineDataSet set1 = new LineDataSet(yValues,"Syke");
            set1.setFillAlpha(110);
            set1.setCircleColor(Color.WHITE);
            set1.setColor(Color.rgb(50, 205, 50));
            set1.setLineWidth(3f);
            set1.setValueTextSize(15f);
            set1.setValueTextColor(Color.BLUE);
            set1.setDrawFilled(true);
            set1.setFillColor(Color.rgb(50, 205, 50));


            sharedPref = getSharedPreferences("Asetukset", Context.MODE_PRIVATE);
            if(sharedPref.getBoolean("Oletusteema", true)) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            } else {
                //If the user has unselected the default theme in the settings, use the theme that was last saved
                if (sharedPref.getBoolean("Tummateema", sharedPref.getBoolean("Oletusteema", true))) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    mChart.getAxisLeft().setTextColor(Color.WHITE);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            mChart.setData(data);

            String[] values = new String[mittaukset.size()];
            for(int i = 0; i < mittaukset.size(); i++){
                values[i] = mittaukset.get(i).getAjankohta();
            }


            LimitLine average_limit = new LimitLine(150f, "     Keskiarvo:  " +
                    Math.round(mittauksetKeskiarvo*100.0)/100.0 + " lyöntiä/min");
            average_limit.setLineWidth(0f);
            average_limit.setLineColor(Color.BLUE);
            average_limit.setTextSize(15f);
            average_limit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
            average_limit.setTextColor(Color.BLUE);
            leftAxis.addLimitLine(average_limit);


            XAxis xAxis = mChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
            xAxis.setGranularity(1f);
            xAxis.setCenterAxisLabels(true);
            xAxis.setEnabled(true);
            xAxis.setDrawGridLines(false);
            //xAxis.setLabelCount(xValues.size(),true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setAxisMaximum(xValues.size()+0.1f);

            mChart.getDescription().setText("Mittauksen ajankohta");
            mChart.getDescription().setTextSize(12f);

            mChart.invalidate(); // Refreshes the graph



        });

    }
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }


}