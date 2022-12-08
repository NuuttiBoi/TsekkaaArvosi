package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.text.*;

public class MainActivity extends AppCompatActivity {

    //creates button
    private Button verenpaineButton, verenHappipitoisuusButton, verensokeriButton, asetuksetButton, testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finds a button from layout with findViewById(R.id.(wanted thing)) command and sets the found value to the created button
        this.verenpaineButton = findViewById(R.id.verenpaineButton);
        this.verenHappipitoisuusButton = findViewById(R.id.verenHappiPitoisuus);
        this.verensokeriButton = findViewById(R.id.verenSokeri);
        this.asetuksetButton = findViewById(R.id.asetukset);
        this.testButton = findViewById(R.id.testiButton);
    }

    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void vPaineButtonPressed(View v) {
        Intent vPaine = new Intent(this, VerenpaineKirjausActivity.class);
        startActivity(vPaine);
    }

    public void vhpButtonPressed(View v) {
        Intent VHP = new Intent(this, VerenHappipitoisuusActivity.class);
        startActivity(VHP);
    }

    public void vsButtonPressed(View v) {
        Intent VeSo = new Intent(this, VerenSokeriActivity.class);
        startActivity(VeSo);
    }

    public void settingsButtonPressed(View v) {
        Intent settings = new Intent(this, Asetukset.class);
        startActivity(settings);
    }

    //muistakaa laittaa class tohon alle huutomerkin tilalle. Menee samallalailla ku noissa muissa
    public void testButtonPressed(View v) {
        Intent test = new Intent(this, TestiActivity4.class);
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

    public void kalenteriButtonPressed(View v) {
        Intent kalenteri = new Intent(this, KalenteriTesti.class);
        startActivity(kalenteri);
    }
}