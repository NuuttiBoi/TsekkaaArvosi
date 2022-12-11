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
    private TextView kayttaja;
    public final static String EXTRA_TEXT = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asetukset);

        this.ilmoitukset = findViewById(R.id.ilmoituksetButton);
        this.teema = findViewById(R.id.teemaButton);
        this.omatTiedot = findViewById(R.id.omatTiedotButton);
        this.backbutton = findViewById(R.id.BackButton);
        this.kayttaja = findViewById(R.id.kayttajaTextView);

        Intent tallenna = getIntent();
        String message = tallenna.getStringExtra(tallenna.EXTRA_TEXT);
        kayttaja.setText(message);
    }

    public void ilmoituksetButtonPressed(View v){
        Intent ilmoitukset = new Intent(this, MuistutuksetActivity.class);
        startActivity(ilmoitukset);
    }

    public void teemaButtonPressed(View v){
        Intent teema = new Intent(this, TeemaActivity.class);
        startActivity(teema);
    }

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


