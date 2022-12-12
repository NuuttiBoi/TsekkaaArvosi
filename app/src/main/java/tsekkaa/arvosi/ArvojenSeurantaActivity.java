package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;

/**
 * Activity lets you choose which graph you want to see
 * When chosen takes you to that activity
 * @author Nuutti Turunen
 */
public class ArvojenSeurantaActivity extends AppCompatActivity {

    private Button sykeButton, alapaineButton, ylapaineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvojen_seuranta);

        this.sykeButton = findViewById(R.id.sykeButton);
        this.alapaineButton = findViewById(R.id.alapaineButton);
        this.ylapaineButton = findViewById(R.id.ylapaineButton);

    }

    /**
     * Sends the user to sykegraafiactivity
     * @param v Button that is pressed
     */
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void sykeButton(View v){
        Intent aLaheta = new Intent(this, SykeGraafiActivity.class);
        startActivity(aLaheta);
    }
    /**
     * Sends the user to ylapainegraafiactivity
     * @param v Button that is pressed
     */
    public void ylapaineButton(View v){
        Intent aLaheta = new Intent(this, YlapaineGraafiActivity.class);
        startActivity(aLaheta);
    }
    /**
     * Sends the user to alapainengraafiactivity
     * @param v Button that is pressed
     */
    public void alapaineButton(View v){
        Intent aLaheta = new Intent(this, AlapaineGraafiActivity.class);
        startActivity(aLaheta);
    }
}