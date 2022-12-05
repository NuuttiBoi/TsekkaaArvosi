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
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TestiActivity extends AppCompatActivity {

    private Calendar mCalendar;

    private GraphView graph;
    private LineGraphSeries<DataPoint> lineSeries;
    private PointsGraphSeries<DataPoint> pointSeries;
    private MittausViewModel mittausViewModel;
    private DataPoint[] dataPoints;

    //Nää voi poistaa jos ei tarvi
    private LineGraphSeries<DataPoint> lineSeries2;
    private DataPoint[] dataPoints2;

    //Viewportin koko mikä siit on kerrallaan näkyvissä, voi muuttaa
    private final static int VIEWPORT_SIZE = 5;

    //Korjatkaa jos on väärää tietoa ^^'
    private final static double VERENSOKERIN_ALARAJA = 6.0;
    private final static double VERENSOKERIN_YLARAJA = 7.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi);
        SimpleDateFormat sdf = new SimpleDateFormat("dd");

        mCalendar = Calendar.getInstance();

        graph = findViewById(R.id.graph);
        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);

        mittausViewModel.haeMittaukset().observe(this, mittaukset -> {
            dataPoints = new DataPoint[mittaukset.size()];
            dataPoints2 = new DataPoint[mittaukset.size()];

            for (int i = 0; i < mittaukset.size(); i++) {
                //Ton verensokerin tilalle voi vaihtaa sen infon mitä haluu
                int x = 0;
                dataPoints[i] = new DataPoint(i, mittaukset.get(i).getVerensokeri());
                //Jos haluu useemman viivan samaan kaavioon esim.
                //dataPoints2[i] = new DataPoint(i, mittaukset.get(i).getHappipitoisuus());
            }
            lineSeries = new LineGraphSeries<>(dataPoints);
            pointSeries = new PointsGraphSeries<>(dataPoints);

            graph.addSeries(lineSeries);
            graph.addSeries(pointSeries);

            /* Voi lisää useemman
            lineSeries2 = new LineGraphSeries<>(dataPoints2);
            graph.addSeries(lineSeries2);
            */

            graph.getViewport().setXAxisBoundsManual(true);

            graph.getViewport().setMinX(dataPoints.length-VIEWPORT_SIZE);
            graph.getViewport().setMaxX(dataPoints.length);

            //graph.getViewport().setMinX(-5000);
            //graph.getViewport().setMaxX(5000);

            //Näit voi laittaa falseks jos haluu, en tiiä mikä ois paras?
            graph.getViewport().setScrollable(true);
            graph.getViewport().setScrollableY(true);
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

            //Näyttää arvon, kun data pointista klikkaa ja varoittaa jos se on liian matala/korkea
            pointSeries.setOnDataPointTapListener((series, dataPoint) -> {
                String teksti = dataPoint.getY() + " mmol/l";
                String varoitus;
                if (dataPoint.getY() > VERENSOKERIN_YLARAJA) {
                    varoitus = "\n+" + String.format("%.2f", dataPoint.getY()-VERENSOKERIN_YLARAJA) + " mmol/l";
                    teksti += varoitus;
                } else if (dataPoint.getY() < VERENSOKERIN_ALARAJA) {
                    varoitus = "\n-" + String.format("%.2f", VERENSOKERIN_ALARAJA-dataPoint.getY()) + " mmol/l";
                    teksti += varoitus;
                }
                Toast toast = Toast.makeText(TestiActivity.this, teksti, Toast.LENGTH_LONG);
                toast.show();
            });

            graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
                @Override
                public String formatLabel(double value, boolean isValueX) {

                        if (isValueX) {
                            //x-akselin label
                            //En keksiny miten tähän saa näkyviin esim. pvm/ajan -__-
                            return super.formatLabel(value,true);
                        } else {
                            //y-akselin label
                            return super.formatLabel(value, isValueX);
                        }
                    }
            });
        });
    }
}