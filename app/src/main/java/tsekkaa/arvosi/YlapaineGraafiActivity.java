package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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


public class YlapaineGraafiActivity extends AppCompatActivity {

    private static final String TAG = "TestiActivity";

    private LineChart mChart;
    private LineGraphSeries<DataPoint> lineSeries;
    private PointsGraphSeries<DataPoint> pointSeries;
    private MittausViewModel mittausViewModel;
    private TextView yksikkoVpTextView;
    private DataPoint[] dataPoints;
    private Button backButton;
    private double mittauksetKaikki, mittauksetKeskiarvo;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ylapaine_graafi);

        mChart = (LineChart) findViewById(R.id.linechart);
        this.backButton = findViewById(R.id.backButton);
        this.yksikkoVpTextView = findViewById(R.id.yksikkoVpTextView);

        //mChart.setOnChartGestureListener(TestiActivity.this);
        //mChart.setOnChartValueSelectedListener(TestiActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        LimitLine upper_limit = new LimitLine(135, "Liian korkea");
        upper_limit.setLineWidth(1f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);


        LimitLine lower_limit = new LimitLine(0, "");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(0f);
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

                yValues.add(new Entry(i, mittaukset.get(i).getYlapaine().floatValue()));
                mittauksetKaikki = mittauksetKaikki + mittaukset.get(i).getYlapaine();
            }

            mittauksetKeskiarvo = (mittauksetKaikki / mittaukset.size());

            LimitLine average_limit = new LimitLine(170f, "     Keskiarvo:  "+
                    Math.round(mittauksetKeskiarvo*100.0)/100.0 + " mmHg");
            average_limit.setLineWidth(0f);
            average_limit.setLineColor(Color.BLUE);
            average_limit.setTextSize(15f);
            average_limit.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
            average_limit.setTextColor(Color.BLUE);
            leftAxis.addLimitLine(average_limit);

            LineDataSet set1 = new LineDataSet(yValues,"Ylapaine");
            set1.setFillAlpha(110);
            set1.setCircleColor(Color.WHITE);
            set1.setColor(Color.rgb(50, 205, 50));
            set1.setLineWidth(3f);
            set1.setValueTextSize(15f);
            set1.setValueTextColor(Color.BLUE);
            set1.setDrawFilled(true);
            set1.setFillColor(Color.rgb(50, 205, 50));


            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            mChart.setData(data);

            String[] values = new String[mittaukset.size()];
            for(int i = 0; i < mittaukset.size(); i++){
                values[i] = mittaukset.get(i).getAjankohta();
            }

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

            mChart.invalidate(); // Refreshes the graph



        });

    }
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }


}