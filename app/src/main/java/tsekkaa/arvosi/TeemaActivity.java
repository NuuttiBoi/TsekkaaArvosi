package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class TeemaActivity extends AppCompatActivity {
    //Creates switches
    private Switch oletusTSwitch, tummaTSwitch;
    //Creates booleans
    private boolean oletusTeema, tummaTeema;
    //Creates a button
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teema);
        getSupportActionBar().hide();

        backButton = findViewById(R.id.teemaBackButton);

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
            tummaTSwitch.setAlpha(.5f);
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
            tummaTSwitch.setAlpha(.5f);
            Log.d("", "Oletusteema päällä");

        } else {
            //Teeman pääsee vaihtamaan itse vain jos oletusteema ei ole valittuna
            tummaTSwitch.setClickable(true);
            tummaTSwitch.setAlpha(1f);

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
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void siirryTakaisin(View view) {
        Intent takaisin = new Intent(this, Asetukset.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
    /*
                NightMode = AppCompatDelegate.getDefaultNightMode();

                sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
                editor = sharedPreferences.edit();

                editor.putInt("NightModeInt", NightMode);
                editor.apply();
                */

    }

}