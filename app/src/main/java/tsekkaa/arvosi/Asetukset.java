package tsekkaa.arvosi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Asetukset extends AppCompatActivity {
    private Button kirjauduUlos;
    private Button ilmoitukset;
    private Button teema;
    private Button omatTiedot;
    private Button backbutton;

    public Asetukset() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asetukset);

        this.kirjauduUlos = findViewById(R.id.kirjauduUlosButton);
        this.ilmoitukset = findViewById(R.id.ilmoituksetButton);
        this.teema = findViewById(R.id.teemaButton);
        this.omatTiedot =findViewById(R.id.omatTiedotButton);
        this.backbutton = findViewById(R.id.BackButton);

    }

    /** Päätetty jättää sisään ja uloskirjautuminen pois
     * public void kirjauduUlosButtonpressed(View asetusnakyma){
        Intent intentKirjauduUlos = new Intent(this, MessageActivity.class);
        startActivity(intentKirjauduUlos); teksti Olet kirjautunut ulos sovelluksesta
    }**/
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
}


