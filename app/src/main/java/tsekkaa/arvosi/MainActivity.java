package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.*;
//Riku Nokelainen
//Frontpage
public class MainActivity extends AppCompatActivity {

    //Creates buttons
    private Button kalenteriButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        //Checks if one of the lists tag is clicked and after clicking one of them it takes you to the selected activity
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent verenpaine = new Intent(MainActivity.this, VerenpaineKirjausActivity.class);
                Intent verenhappipitoisuus = new Intent(MainActivity.this, VerenHappipitoisuusKirjausActivity.class);
                Intent verensokeri = new Intent(MainActivity.this, VerenSokeriKirjausActivity.class);
                Intent asetukset = new Intent(MainActivity.this, Asetukset.class);
                Intent testi = new Intent(MainActivity.this, TestiActivity.class);
                if(i == 0){
                    startActivity(verenpaine);
                } else if(i == 1){
                    startActivity(verenhappipitoisuus);
                } else if(i == 2){
                    startActivity(verensokeri);
                } else if(i == 3){
                    startActivity(asetukset);
                } else{
                    startActivity(testi);
                }
            }
        });
}
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void testButtonPressed(View v){
        Intent test = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            test = new Intent(this, KalenteriActivity.class);
        }
        startActivity(test);

        //zoom in
        //overridePendingTransition(R.anim.zoom_in, R.anim.static_anim);

        //zoom out
        //overridePendingTransition(R.anim.static_anim, R.anim.zoom_out);

        //slide ylös
        //overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);

        //slide alas
        //overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);

        //slide vasemmalle
        //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        //slide oikealle
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
    public void KalenteriButtonPressed(View v) {
        Intent kalenteri = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            kalenteri = new Intent(this, KalenteriActivity.class);
        }
        startActivity(kalenteri);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
