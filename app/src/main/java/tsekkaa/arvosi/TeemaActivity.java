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

/**
 *
 * @author Matleena
 */
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
        //Finds a button from layout with findViewById(R.id.(name of the button)) command and sets the found value to the created button
        backButton = findViewById(R.id.teemaBackButton);
        //Finds a Switch from layout with findViewById(R.id.(name of the button)) command and sets the found value to the created switch
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
    //updates the set information to the places where the method is called
    private void updateUI() {
        if (oletusTSwitch.isChecked()) {
            //Sets the theme automaticly from phones settings
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            tummaTSwitch.setClickable(false);
            tummaTSwitch.setAlpha(.5f);
            Log.d("", "Oletusteema päällä");

        } else {
            //Theme can be changed if the default theme is not selected
            tummaTSwitch.setClickable(true);
            tummaTSwitch.setAlpha(1f);

            Log.d("", "sharedpreferences: " + tummaTeema);

            vaihdaTeema(tummaTeema);
            //Checks if the switch is clicked and changes between switches settings
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
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void siirryTakaisin(View view) {
        Intent takaisin = new Intent(this, Asetukset.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}