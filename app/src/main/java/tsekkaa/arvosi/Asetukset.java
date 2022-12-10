package tsekkaa.arvosi;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Asetukset extends AppCompatActivity {
    private Button ilmoitukset;
    private Button teema;
    private Button omatTiedot;
    private Button backbutton;


    public Asetukset() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asetukset);

        this.ilmoitukset = findViewById(R.id.ilmoituksetButton);
        this.teema = findViewById(R.id.teemaButton);
        this.omatTiedot = findViewById(R.id.omatTiedotButton);
        this.backbutton = findViewById(R.id.BackButton);
    }

    public void ilmoituksetButtonPressed(View v){

    }

    public void teemaButtonPressed(View v){
        Intent teema = new Intent(this, TeemaActivity.class);
        startActivity(teema);
    }

    public void omatTiedotButtonPressed(View v){

    }
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
}


