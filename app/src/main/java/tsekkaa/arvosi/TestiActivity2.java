package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Switch;

import java.util.ArrayList;
import com.google.gson.Gson;

public class TestiActivity2 extends AppCompatActivity {
    private Button btn2;
    private MittausViewModel mittausViewModel;
    private Switch oletusTSwitch, tummaTSwitch;
    private boolean oletusTeema, tummaTeema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi2);
        //mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);


        btn2 = findViewById(R.id.btn2);
        oletusTSwitch = findViewById(R.id.oletusTSwitch);
        tummaTSwitch = findViewById(R.id.tummaTSwitch);
        SharedPreferences sharedPref = getSharedPreferences("Asetukset", Context.MODE_PRIVATE);
        oletusTeema = sharedPref.getBoolean("Oletusteema", true);
        tummaTeema = sharedPref.getBoolean("Tummateema", oletusTeema);
        SharedPreferences.Editor editor = sharedPref.edit();


        oletusTSwitch.setChecked(oletusTeema);
        tummaTSwitch.setChecked(tummaTeema);

        if (oletusTSwitch.isChecked()) {
            tummaTSwitch.setClickable(false);
        }

        //updateUI();

        //SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putInt("visibles", counterVisibles.getValue());
        //editor.putInt("hits", counterHits.getValue());
        //editor.apply();

        btn2.setOnClickListener(view -> {
            //mittausViewModel.lisaaMittaus(new Mittaus(yläpaine doublena, alapaine doublena, syke doublena, verensokeri doublena, happipitoisuus doublena, päivä intinä, kuukausi intinä, vuosi intinä, tunnit intinä, minuutit intinä));
            //Intent intent = new Intent(this, TestiActivity.class);
            //startActivity(intent);

            Log.d("", "" + oletusTeema);
        });

        oletusTSwitch.setOnClickListener(view -> {
            oletusTeema = !oletusTeema;
            editor.putBoolean("Oletusteema", oletusTeema);
            editor.apply();
            updateUI();
        });
    }

    private void updateUI() {
        if (oletusTSwitch.isChecked() == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            tummaTSwitch.setClickable(false);
            Log.d("", "=letusteema päällä");
        } else {
            tummaTSwitch.setClickable(true);

            tummaTSwitch.setOnClickListener(view -> {
                tummaTeema = !tummaTeema;

                SharedPreferences sharedPref = getSharedPreferences("Asetukset", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Tummateema", tummaTeema);
                editor.apply();

                updateUI();
            });

            Log.d("", "Oletusteema pois");

        }
        Log.d("", "Oletus teema: " + oletusTeema);
        Log.d("", "Tumma teema: " + tummaTeema);

    }
}