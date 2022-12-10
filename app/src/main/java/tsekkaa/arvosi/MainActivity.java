package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //creates button
    private Button verenpaineButton, verenHappipitoisuusButton, verensokeriButton, asetuksetButton, testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finds a button from layout with findViewById(R.id.(wanted thing)) command and sets the found value to the created button

        this.testButton = findViewById(R.id.testiButton);
        ListView lv = findViewById(R.id.activitySelection);

        lv.setAdapter(new ArrayAdapter<NextActivity>(
                this,  //context (activity instance)
                android.R.layout.simple_list_item_1,
                ActivitySelectionSingleton.getInstance().getActivity()) //array of data
        );
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
    public void vPaineButtonPressed(View v){
        Intent vPaine = new Intent(this, VerenpaineKirjausActivity.class);
        startActivity(vPaine);
    }
    public void vhpButtonPressed(View v){
        Intent VHP = new Intent(this, VerenHappipitoisuusKirjausActivity.class);
        startActivity(VHP);
    }
    public void vsButtonPressed(View v){
        Intent VeSo = new Intent(this, VerenSokeriKirjausActivity.class);
        startActivity(VeSo);
    }
    public void settingsButtonPressed(View v){
        Intent settings = new Intent(this, Asetukset.class);
        startActivity(settings);
    }
    //muistakaa laittaa class tohon alle huutomerkin tilalle. Menee samallalailla ku noissa muissa
    public void testButtonPressed(View v){
        Intent test = new Intent(this, KalenteriActivity.class);
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
}