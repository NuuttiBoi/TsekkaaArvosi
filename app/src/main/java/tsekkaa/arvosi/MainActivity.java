package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.*;

/**
 * Tsekkaa Arvosi apps frontpage
 * @author Riku Nokelainen
 */
public class MainActivity extends AppCompatActivity {

    //Creates buttons
    private Button kalenteriButton;
    //Creates SharedPreferences
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        //Finds a button from layout with findViewById(R.id.(name of the button)) command and sets the found value to the created button
        this.kalenteriButton = findViewById(R.id.kalenteriButton);
        //Finds listview component from layout with findViewById(R.id.(name of the listview)
        ListView lv = findViewById(R.id.activitySelection);
        //Uses Singleton and NextActivity classes to create the list to the listview component
        lv.setAdapter(new ArrayAdapter<NextActivity>(
                this,  //context (activity instance)
                android.R.layout.simple_list_item_1,
                ActivitySelectionSingleton.getInstance().getActivity()) //array of data
        );

        /**
         * Checks if one of the lists tag is clicked and after clicking one of them it takes you to the selected activity
         */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent verenpaine = new Intent(MainActivity.this, VerenpaineKirjausActivity.class);
                Intent verenhappipitoisuus = new Intent(MainActivity.this, VerenHappipitoisuusKirjausActivity.class);
                Intent verensokeri = new Intent(MainActivity.this, VerenSokeriKirjausActivity.class);
                Intent asetukset = new Intent(MainActivity.this, Asetukset.class);
                //Intent testi = new Intent(MainActivity.this, TestiActivity.class);
                if(i == 0){
                    startActivity(verenpaine);
                } else if(i == 1){
                    startActivity(verenhappipitoisuus);
                } else if(i == 2){
                    startActivity(verensokeri);
                } else if(i == 3){
                    startActivity(asetukset);
                } else{
                    //startActivity(testi);
                }
            }
        });

        //Use the device's default theme if no other theme is selected or the app is started for the first time
        sharedPref = getSharedPreferences("Asetukset", Context.MODE_PRIVATE);
        if(sharedPref.getBoolean("Oletusteema", true)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else {
            //If the user has unselected the default theme in the settings, use the theme that was last saved
            if (sharedPref.getBoolean("Tummateema", sharedPref.getBoolean("Oletusteema", true))) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
}

    /**
     * Takes you to calender view
     * @param v Button that is pressed
     */
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void KalenteriButtonPressed(View v) {
        Intent kalenteri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            kalenteri = new Intent(this, KalenteriActivity.class);
        }
        startActivity(kalenteri);
        //zoom in
        //overridePendingTransition(R.anim.zoom_in, R.anim.static_anim);

        //zoom out
        //overridePendingTransition(R.anim.static_anim, R.anim.zoom_out);

        //slide up
        //overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);

        //slide down
        //overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);

        //slide to the left
        //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        //slide to the right
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
