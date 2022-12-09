package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;


public class VerenpaineGraafiActivity extends AppCompatActivity {

    private Calendar mCalendar;

    private GraphView graph;
    private LineGraphSeries<DataPoint> lineSeries;
    private PointsGraphSeries<DataPoint> pointSeries;
    private MittausViewModel mittausViewModel;
    private DataPoint[] dataPoints;
    private SimpleDateFormat sdf;
    private boolean start = true;

    //Viewportin koko mikä siit on kerrallaan näkyvissä, voi muuttaa
    private final static int VIEWPORT_SIZE = 2;

    //Korjatkaa jos on väärää tietoa ^^'
    private final static double YLAPAINEEN_ALARAJA = 100.0;
    private final static double YLAPAINEEN_YLARAJA = 130.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        boolean recreateRequested = true;
        Intent currentIntent = getIntent();
        if (currentIntent.hasExtra("restart")){
            recreateRequested = currentIntent.getBooleanExtra("restart", true);
        }
        if (recreateRequested) {
            Intent intent = new Intent(this, VerenpaineKirjausActivity.class);
            intent.putExtra("restartti", false);
            startActivity(intent);
            finish();
        }

         */
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
                int kuukausiArray[] = new int[mittaukset.size()];

                dataPoints = new DataPoint[mittaukset.size()];

                LineGraphSeries<DataPoint> LineSeries =
                        new LineGraphSeries<>();
                for (int i = 0; i < mittaukset.size(); i++) {
                    for (int j = 0; j < mittaukset.size(); j++) {
                        aikaArray[j] = mittaukset.get(j).getAika();
                    }
                    Arrays.sort(aikaArray);
                    Log.d("h", "v " + mittaukset.size() + new Date(aikaArray[i]));
                    //Ton verensokerin tilalle voi vaihtaa sen infon mitä haluu

                    LineSeries.appendData(new DataPoint(new Date(aikaArray[i]), mittaukset.get(i).getYlapaine()), false,
                            mittaukset.size());
                    dataPoints[i] = new DataPoint(new Date(aikaArray[i]), mittaukset.get(i).getYlapaine());
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

                graph.setTitle("Yläpaineen viimeaikaiset mittaustuloksesi:");
                graph.getViewport().setScrollable(true);
                graph.getViewport().setScrollableY(true);
                //graph.getViewport().setScalable(true);
                //graph.getViewport().setScalableY(true);

                LineSeries.setDrawBackground(true);
                graph.getGridLabelRenderer().setHumanRounding(false);
                graph.getGridLabelRenderer().setHorizontalLabelsAngle(50);

                // Asettaa otsikot akseleille
                graph.getGridLabelRenderer().setVerticalAxisTitle("Yläpaina (mmHg)");
                graph.getGridLabelRenderer().setPadding(20);
                graph.getGridLabelRenderer().setHorizontalAxisTitle("Mittauksen ajankohta");

                //Näyttää arvon, kun data pointista klikkaa ja varoittaa jos se on liian matala/korkea

                pointSeries.setOnDataPointTapListener((series, dataPoint) -> {
                    String teksti = dataPoint.getY() + " mmHg";
                    String varoitus;
                    if (dataPoint.getY() > YLAPAINEEN_YLARAJA) {
                        varoitus = "\n+" + String.format("%.2f", dataPoint.getY() - YLAPAINEEN_YLARAJA) + " mmol/l";
                        teksti += varoitus;
                    } else if (dataPoint.getY() < YLAPAINEEN_ALARAJA) {
                        varoitus = "\n-" + String.format("%.2f", YLAPAINEEN_ALARAJA - dataPoint.getY()) + " mmol/l";
                        teksti += varoitus;
                    }
                    Toast toast = Toast.makeText(VerenpaineGraafiActivity.this, teksti, Toast.LENGTH_LONG);
                    toast.show();
                });


                //graph.getGridLabelRenderer().setNumHorizontalLabels(5);

        });
    }
}