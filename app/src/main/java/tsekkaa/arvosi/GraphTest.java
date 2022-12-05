package tsekkaa.arvosi;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.Nullable;
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


public class GraphTest extends AppCompatActivity {

    private static final int VIEWPORT_SIZE = 5;
    private MittausViewModel mittausViewModel;
    private DataPoint[] dataPoints;
    private PointsGraphSeries<DataPoint> pointSeries;
    private LineGraphSeries<DataPoint> lineSeries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_test);

        GraphView graph=(GraphView) findViewById(R.id.graph);
        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

        mittausViewModel.haeMittaukset().observe(this, mittaukset -> {
            dataPoints = new DataPoint[mittaukset.size()];
            // dataPoints2 = new DataPoint[mittaukset.size()];

            int numero_yksi;
            int numero_kaksi;
            int paivaArray[] = new int[mittaukset.size()];
            int kuukausiArray[] = new int[mittaukset.size()];
            Date dateArray[] = new Date[mittaukset.size()];
            int ok = mittaukset.get(0).getPaiva();
            int a;
            double x;
            double y;
            int xd = 2022;

            for (int i = 0; i < mittaukset.size(); i++) {

                    for(int j = 0; j < mittaukset.size(); j++){
                        paivaArray[j] = mittaukset.get(j).getPaiva();
                        if(mittaukset.get(j).getKuukausi()<=12) {
                            kuukausiArray[j] = mittaukset.get(j).getKuukausi();
                        }
                    }
                Arrays.sort(kuukausiArray);
                //Arrays.sort(paivaArray);

                //Ton verensokerin tilalle voi vaihtaa sen infon mitä haluu

                a = kuukausiArray[i];
                x = Double.valueOf(a);
                Log.d("ok","paska " + x);
                y = mittaukset.get(i).getYlapaine();

                series.appendData(new DataPoint(x,y),true,100);
                Log.d("bye","tulokset " + mittaukset.get(i).getPaiva());
            }

            LineGraphSeries<DataPoint> okseries = new LineGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(mittaukset.get(2).getPvmKK(), 1),
            });
            graph.addSeries(okseries);

            graph.addSeries(series);





            // lineSeries = new LineGraphSeries<>(dataPoints);
            // pointSeries = new PointsGraphSeries<>(dataPoints);

            // graph.addSeries(lineSeries);
            // graph.addSeries(pointSeries);

            /* Voi lisää useemman
            lineSeries2 = new LineGraphSeries<>(dataPoints2);
            graph.addSeries(lineSeries2);
            */


        });

    }

}

