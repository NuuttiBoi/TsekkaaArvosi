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
    //Creates buttons
    private Button ilmoitukset;
    private Button teema;
    private Button omatTiedot;
    private Button backbutton;
    //Creates textview
    private TextView kayttaja;
    public final static String EXTRA_TEXT = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asetukset);
        //Finds a button from layout with findViewById(R.id.(name of the button)) command and sets the found value to the created button
        this.ilmoitukset = findViewById(R.id.ilmoituksetButton);
        this.teema = findViewById(R.id.teemaButton);
        this.omatTiedot = findViewById(R.id.omatTiedotButton);
        this.backbutton = findViewById(R.id.BackButton);
        //Finds textview from layout with findViewById(R.id.(name of the textview)) command and sets the found value to the created textview
        this.kayttaja = findViewById(R.id.kayttajaTextView);

        Intent tallenna = getIntent();
        String message = tallenna.getStringExtra(tallenna.EXTRA_TEXT);
        kayttaja.setText(message);
    }
    //Checks if one of the lists tag is clicked and after clicking one of them it takes you to the selected activity
    public void ilmoituksetButtonPressed(View v){
        Intent ilmoitukset = new Intent(this, MuistutuksetActivity.class);
        startActivity(ilmoitukset);
    }
    //Checks if one of the lists tag is clicked and after clicking one of them it takes you to the selected activity
    public void teemaButtonPressed(View v){
        Intent teema = new Intent(this, TeemaActivity.class);
        startActivity(teema);
    }
    //Checks if one of the lists tag is clicked and after clicking one of them it takes you to the selected activity
    public void omatTiedotButtonPressed(View v){
        Intent omatTiedot = new Intent(this, OmatTiedot.class);
        startActivity(omatTiedot);
    }

    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
}


