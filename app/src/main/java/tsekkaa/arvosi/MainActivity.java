package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.text.*;

public class MainActivity extends AppCompatActivity {

    //luo buttonin
    private Button verenpaineButton, verenHappipitoisuusButton, verensokeriButton, asetuksetButton, testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.verenpaineButton = findViewById(R.id.verenpaineButton);
        this.verenHappipitoisuusButton = findViewById(R.id.verenHappiPitoisuus);
        this.verensokeriButton = findViewById(R.id.verenSokeri);
        this.asetuksetButton = findViewById(R.id.asetukset);
        this.testButton = findViewById(R.id.testiButton);
}
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void vPaineButtonPressed(View v){
        Intent vPaine = new Intent(this, VerenpaineKirjausActivity.class);
        startActivity(vPaine);
    }
    public void vhpButtonPressed(View v){
        Intent VHP = new Intent(this, VerenHappipitoisuusActivity.class);
        startActivity(VHP);
    }
    public void vsButtonPressed(View v){
        Intent VeSo = new Intent(this, VerenSokeriActivity.class);
        startActivity(VeSo);
    }
    public void settingsButtonPressed(View v){
        Intent settings = new Intent(this, Asetukset.class);
        startActivity(settings);
    }
    //muistakaa laittaa class tohon alle huutomerkin tilalle. Menee samallalailla ku noissa muissa
    public void testButtonPressed(View v){
        Intent test = new Intent(this, TestiActivity2.class);
        startActivity(test);


    }
}