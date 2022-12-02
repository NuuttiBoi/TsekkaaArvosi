package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button verenpaineButton, verenHappipitoisuusButton, verensokeriButton, asetuksetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.verenpaineButton = findViewById(R.id.verenpaineButton);
        this.verenHappipitoisuusButton = findViewById(R.id.verenHappiPitoisuus);
        this.verensokeriButton = findViewById(R.id.verenSokeri);
        this.asetuksetButton = findViewById(R.id.asetukset);
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
}