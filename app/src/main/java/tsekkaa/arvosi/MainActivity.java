package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button verenpaineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.verenpaineButton =findViewById(R.id.verenpaineButton);
    }
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void vPaineButtonPressed(View v){
        Intent vPaine = new Intent(this, VerenpaineKirjausActivity.class);
        startActivity(vPaine);
    }
    public void asetuksetButtonPressed(View v){
        Intent asetukset = new Intent(this, Asetukset.class);
        startActivity(asetukset);
    }
    public void VerenHappiPitoisuusButtonPressed(View v){
        Intent VHPitoisuus = new Intent(this, VerenHappipitoisuus.class);
        startActivity(VHPitoisuus);
    }
    public void VerenSokeriButtonPressed(View v){
        Intent verensokeri = new Intent(this, VerenSokeri.class);
        startActivity(verensokeri);
    }
    //public void ButtonPressed(View v){
    //    Intent  = new Intent(this, .class);
    //    startActivity();
    //}

}