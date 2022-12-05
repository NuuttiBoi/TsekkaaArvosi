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
    private Switch oletusTSwitch, tummaTSwitch;
    private boolean oletusTeema, tummaTeema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi2);
        getSupportActionBar().hide();

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

        oletusTSwitch.setOnClickListener(view -> {
            oletusTeema = !oletusTeema;
            editor.putBoolean("Oletusteema", oletusTeema);
            editor.apply();
            updateUI();
        });

        tummaTSwitch.setOnClickListener(view -> {
            updateUI();
        });

        updateUI();
    }

    private void updateUI() {
        if (oletusTSwitch.isChecked()) {
            //Asettaa teeman automaattisesti puhelimen asetuksista
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            tummaTSwitch.setClickable(false);
            Log.d("", "Oletusteema päällä");
        } else {
            //Teeman pääsee vaihtamaan itse vain jos oletusteema ei ole valittuna
            tummaTSwitch.setClickable(true);

            Log.d("", "sharedpreferences: " + tummaTeema);

            vaihdaTeema(tummaTeema);

            tummaTSwitch.setOnClickListener(view -> {
                tummaTeema = !tummaTeema;

                SharedPreferences sharedPref = getSharedPreferences("Asetukset", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Tummateema", tummaTeema);
                editor.apply();
                Log.d("", "sharedpreferences: " + tummaTeema);

                vaihdaTeema(tummaTeema);
            });
            Log.d("", "Oletusteema pois");
        }
    }

    private void vaihdaTeema(boolean teema) {
        if (teema) {
            //Jos käyttäjällä on valittuna tumma teema
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //Jos käyttäjällä on valittuna vaalea teema
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}