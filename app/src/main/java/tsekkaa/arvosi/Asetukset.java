package tsekkaa.arvosi;


import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Asetukset extends AppCompatActivity {
    private Button kirjauduUlos;
    private Button ilmoitukset;
    private Button teema;
    private Button omatTiedot;

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

    }

    /** Päätetty jättää sisään ja uloskirjautuminen pois
     * public void kirjauduUlosButtonpressed(View asetusnakyma){
        Intent intentKirjauduUlos = new Intent(this, MessageActivity.class);
        startActivity(intentKirjauduUlos); teksti Olet kirjautunut ulos sovelluksesta
    }**/
}


