package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

/**
 * Switches between light and dark mode
 *
 * @author Matleena
 */
public class TeemaActivity extends AppCompatActivity {
    private Switch oletusTSwitch, tummaTSwitch;
    private boolean oletusTeema, tummaTeema;
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

        /*
        Set both switches to their previously saved states (retrieved from shared preferences)
        By default the system theme is on
         */
        oletusTSwitch.setChecked(oletusTeema);
        tummaTSwitch.setChecked(tummaTeema);


        //Whenever the system theme is on, the other button becomes unclickable and semi-transparent
        if (oletusTSwitch.isChecked()) {
            tummaTSwitch.setClickable(false);
            tummaTSwitch.setAlpha(.5f);
        }

        //The state of the switch is changed on click
        oletusTSwitch.setOnClickListener(view -> {
            oletusTeema = !oletusTeema;

            /*
            Save the current state of the switch every time it's checked, so the exact state of both
            switches can be restored the next time the activity is opened (not just whether dark mode is on or not)
            */
            editor.putBoolean("Oletusteema", oletusTeema);
            editor.apply();
            updateUI();
        });

        tummaTSwitch.setOnClickListener(view -> {
            updateUI();
        });

        updateUI();
    }

    //Updates the color theme and the switches' settings on the UI
    private void updateUI() {
        if (oletusTSwitch.isChecked()) {
            //If the system theme is checked
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            tummaTSwitch.setClickable(false);
            tummaTSwitch.setAlpha(.5f);
        } else {
            //If the system theme is not checked
            tummaTSwitch.setClickable(true);
            tummaTSwitch.setAlpha(1f);
            vaihdaTeema(tummaTeema);

            //The onClickListener for the other button only becomes active when the system theme is not on
            tummaTSwitch.setOnClickListener(view -> {
                //The state of the switch changes on click
                tummaTeema = !tummaTeema;

                SharedPreferences sharedPref = getSharedPreferences("Asetukset", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("Tummateema", tummaTeema);
                editor.apply();
                vaihdaTeema(tummaTeema);
            });
        }
    }

    //Receives the currently selected theme and changes the UI accordingly
    private void vaihdaTeema(boolean teema) {
        if (teema) {
            //If user has dark theme selected
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //If user has light theme selected
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * Takes you back to settings activity
     * @param view Button that is pressed
     */
    public void siirryTakaisin(View view) {
        Intent takaisin = new Intent(this, Asetukset.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}