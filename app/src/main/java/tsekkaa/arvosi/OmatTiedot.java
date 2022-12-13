package tsekkaa.arvosi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * In this activity you can write your name and age here
 * @author Tia Eränen
 */
public class OmatTiedot extends AppCompatActivity {
    private EditText nimi, ika;
    private Button tallenna;
    public final static String EXTRA_TEXT = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omat_tiedot);

        this.nimi = findViewById(R.id.nimiEditText);
        this.ika = findViewById(R.id.ikaEditText);
        this.tallenna = findViewById(R.id.otTallennaButton);
        }
        /*  intentin pitäisi viedä omat tiedot sivulle tallennettu nimi asetusten sivulle niin,
            että sinne saadaan käyttöön teksti Käyttäjä: + nimi
         */
    /**
     * Returns user to the settings activity
     * @param v Button that is pressed
     */
    public void otTallennaButtonPressed(View v){
            Intent tallenna = new Intent(this, Asetukset.class);
            String message = "Käyttäjä: " + nimi;
            tallenna.putExtra(tallenna.EXTRA_TEXT, message);
            startActivity(tallenna);
    }
}
