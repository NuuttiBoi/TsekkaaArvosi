package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
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


public class TestiActivity4 extends AppCompatActivity {

    private Calendar mCalendar;

    private GraphView graph;
    private LineGraphSeries<DataPoint> lineSeries;
    private PointsGraphSeries<DataPoint> pointSeries;
    private MittausViewModel mittausViewModel;
    private DataPoint[] dataPoints;
    private SimpleDateFormat sdf;

    //Nää voi poistaa jos ei tarvi
    private LineGraphSeries<DataPoint> lineSeries2;
    private DataPoint[] dataPoints2;

    //Viewportin koko mikä siit on kerrallaan näkyvissä, voi muuttaa
    private final static int VIEWPORT_SIZE = 2;

    //Korjatkaa jos on väärää tietoa ^^'
    private final static double VERENSOKERIN_ALARAJA = 6.0;
    private final static double VERENSOKERIN_YLARAJA = 7.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi);

        mCalendar = Calendar.getInstance();
        graph = findViewById(R.id.graph);

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
        mittausViewModel.haeMittaukset().observe(this, mittaukset -> {

            /*
            if(mittaukset.size()>10){
                mittaukset.remove(0);
            } else {

             */

                Long aikaArray[] = new Long[mittaukset.size()];

                dataPoints = new DataPoint[mittaukset.size()];
                dataPoints2 = new DataPoint[mittaukset.size()];

                LineGraphSeries<DataPoint> LineSeries =
                        new LineGraphSeries<>();
                for (int i = 0; i < mittaukset.size(); i++) {
                    for (int j = 0; j < mittaukset.size(); j++) {
                        aikaArray[j] = mittaukset.get(j).getAika();
                    }
                    Arrays.sort(aikaArray);
                    Log.d("h", "v " + mittaukset.size() + new Date(aikaArray[i]));
                    //Ton verensokerin tilalle voi vaihtaa sen infon mitä haluu

                    LineSeries.appendData(new DataPoint(new Date(aikaArray[i]), mittaukset.get(i).getSyke()), false,
                            mittaukset.size());
                    dataPoints[i] = new DataPoint(new Date(aikaArray[i]), mittaukset.get(i).getSyke());
                }

                //lineSeries = new LineGraphSeries<>(dataPoints);
                pointSeries = new PointsGraphSeries<>(dataPoints);


                //graph.getViewport().setXAxisBoundsManual(true);
                //graph.getViewport().setMinX(0);
                //graph.getViewport().setMaxX(mittaukset.get(9).getSyke());
                graph.getGridLabelRenderer().setNumHorizontalLabels(10);

                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                    @Override
                    public String formatLabel(double value, boolean isValueX) {
                        if (isValueX) {
                            //x-akselin label
                            //En keksiny miten tähän saa näkyviin esim. pvm/ajan -__-
                            Format formatter = new SimpleDateFormat("MM/dd HH:mm");
                            return formatter.format(value);
                            //return super.formatLabel(value, isValueX);
                        } else {
                            //y-akselin label
                            return super.formatLabel(value, isValueX);
                        }
                    }
                });

                graph.addSeries(LineSeries);
                //graph.addSeries(lineSeries);
                graph.addSeries(pointSeries);


                graph.getViewport().setScrollable(true);
                graph.getViewport().setScrollableY(true);
                //graph.getViewport().setScalable(true);
                //graph.getViewport().setScalableY(true);

                LineSeries.setDrawBackground(true);
                graph.getGridLabelRenderer().setHumanRounding(false);
                graph.getGridLabelRenderer().setHorizontalLabelsAngle(50);

                // Asettaa otsikot akseleille
                graph.getGridLabelRenderer().setVerticalAxisTitle("Syke");
                //graph.getGridLabelRenderer().setPadding(20);
                graph.getGridLabelRenderer().setHorizontalAxisTitle("Mittauksen ajankohta");

                //Näyttää arvon, kun data pointista klikkaa ja varoittaa jos se on liian matala/korkea

                pointSeries.setOnDataPointTapListener((series, dataPoint) -> {
                    String teksti = dataPoint.getY() + " mmol/l";
                    String varoitus;
                    if (dataPoint.getY() > VERENSOKERIN_YLARAJA) {
                        varoitus = "\n+" + String.format("%.2f", dataPoint.getY() - VERENSOKERIN_YLARAJA) + " mmol/l";
                        teksti += varoitus;
                    } else if (dataPoint.getY() < VERENSOKERIN_ALARAJA) {
                        varoitus = "\n-" + String.format("%.2f", VERENSOKERIN_ALARAJA - dataPoint.getY()) + " mmol/l";
                        teksti += varoitus;
                    }
                    Toast toast = Toast.makeText(TestiActivity4.this, teksti, Toast.LENGTH_LONG);
                    toast.show();
                });


                //graph.getGridLabelRenderer().setNumHorizontalLabels(5);

        });
    }
}