package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;


public class TestiActivity extends AppCompatActivity {
    private Button btn;
    private GraphView graph;
    private LineGraphSeries<DataPoint> series;
    private Gson gson;
    private TypeToken<List<Mittaus>> token = new TypeToken<List<Mittaus>>() {};
    private List<Mittaus> arvot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi);

        Intent intent = getIntent();
        String arvotJson = intent.getStringExtra(TestiActivity2.EXTRA_LISTA);
        gson = new Gson();
        token = new TypeToken<List<Mittaus>>() {};
        arvot = gson.fromJson(arvotJson, token.getType());


        btn = findViewById(R.id.btn);
        graph = (GraphView) findViewById(R.id.graph);


        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        Log.d("", "STRING ACTIVITYSTA: " + arvotJson);
    }
}