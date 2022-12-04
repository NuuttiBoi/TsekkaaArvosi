package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import java.util.ArrayList;
import com.google.gson.Gson;

public class TestiActivity2 extends AppCompatActivity {
    private Button btn2;
    private ArrayList<Mittaus> arvot;
    public final static String EXTRA_LISTA = "com.example.tsekkaa.arvosi.NAME";
    private MittausViewModel mittausViewModel;
    Gson gson;
    String jsonArvot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi2);

        btn2 = findViewById(R.id.btn2);
        arvot = new ArrayList<>();
        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
        gson = new Gson();

        mittausViewModel.haeMittaukset().observe(this, mittaukset -> {
            for (int i = (mittaukset.size()-1); i > (mittaukset.size()-8); i--) {
                arvot.add(mittaukset.get(i));
            }
            jsonArvot = gson.toJson(arvot);
        });

        btn2.setOnClickListener(view -> {
                //Log.d("", "listan pituus: " + arvot.size());
                Intent intent = new Intent(this, TestiActivity.class);

                Log.d("", "testiactivity2: " + jsonArvot);

                intent.putExtra(EXTRA_LISTA, jsonArvot);
                startActivity(intent);
        });

    }
}