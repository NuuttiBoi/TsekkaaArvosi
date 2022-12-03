package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;


public class TestiActivity extends AppCompatActivity {
    private Button btn;
    private GraphView graph;
    private LineGraphSeries<DataPoint> series;
    private ArrayList<Mittaus> arvot;
    MittausViewModel mittausViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi);

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);

        btn = findViewById(R.id.btn);
        graph = (GraphView) findViewById(R.id.graph);
        arvot = new ArrayList<>();

        btn.setOnClickListener(view -> {
            mittausViewModel.lisaaMittaus(new Mittaus(171.0, 24.0, 50.0, 90.0, 100.0, 03, 12, 2022, 20, 58));
        });

        mittausViewModel.haeMittaukset().observe(this, mittaukset -> {
            for (int i = 0; i < mittaukset.size(); i++) {
                Log.d("", "yp: " + mittaukset.get(i).getYlapaine()
                        + ", ap: " + mittaukset.get(i).getAlapaine()
                        + ", syke: " + mittaukset.get(i).getSyke()
                        + ", vsok: " + mittaukset.get(i).getVerensokeri()
                        + ", vhappi: " + mittaukset.get(i).getHappipitoisuus()
                        + " " + mittaukset.get(i).getPaiva() + "." + mittaukset.get(i).getKuukausi() + "." + mittaukset.get(i).getVuosi()
                        + " " + mittaukset.get(i).getTunnit() + ":" + mittaukset.get(i).getMinuutit());
            }
        });


        mittausViewModel.haeMittaukset().observe(this, mittaukset -> {
            for (int i = (mittaukset.size()-1); i > (mittaukset.size()-8); i--) {
                arvot.add(mittaukset.get(i));
            }
        });

        series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, arvot.size()),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }
}