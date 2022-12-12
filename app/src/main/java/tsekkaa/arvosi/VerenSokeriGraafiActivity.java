package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class VerenSokeriGraafiActivity extends AppCompatActivity {

    private static final String TAG = "TestiActivity";

    private LineChart mChart;
    private LineGraphSeries<DataPoint> lineSeries;
    private PointsGraphSeries<DataPoint> pointSeries;
    private MittausViewModel mittausViewModel;
    private Button backButton;

    //Korjatkaa jos on väärää tietoa ^^'
    private final static double VERENSOKERIN_ALARAJA = 6.0;
    private final static double VERENSOKERIN_YLARAJA = 7.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veren_sokeri_graafi);

        this.backButton = findViewById(R.id.backButton);
        mChart = (LineChart) findViewById(R.id.verensokeriLinechart);

        //mChart.setOnChartGestureListener(TestiActivity.this);
        //mChart.setOnChartValueSelectedListener(TestiActivity.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        LimitLine upper_limit = new LimitLine(7, "Liian korkea");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);


        LimitLine lower_limit = new LimitLine(6, "Liian matala");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaximum(15f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
        mittausViewModel.haeVerensokeriMittaukset().observe(this, mittaukset -> {

            String pvm[] = new String[mittaukset.size()];

            ArrayList<Entry> yValues = new ArrayList<>();
            ArrayList xValues = new ArrayList<>();
            for (int i = 0; i < mittaukset.size(); i++) {
                pvm[i] = mittaukset.get(i).getAjankohta();
                xValues.add(pvm[i]);
            }

            for (int i = 0; i < mittaukset.size(); i++) {

                yValues.add(new Entry(i, mittaukset.get(i).getVerensokeri().floatValue()));

            }

            LineDataSet set1 = new LineDataSet(yValues, "Verensokeri");
            set1.setFillAlpha(110);
            set1.setColor(Color.BLUE);
            set1.setLineWidth(3f);
            set1.setValueTextSize(10f);
            set1.setValueTextColor(Color.BLUE);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            mChart.setData(data);

            String[] values = new String[mittaukset.size()];
            for (int i = 0; i < mittaukset.size(); i++) {
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
            xAxis.setAxisMaximum(xValues.size() + 0.1f);

            mChart.getDescription().setText("Mittauksen ajankohta");

            mChart.invalidate(); // Refreshes the graph


        });
    }

    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }

}