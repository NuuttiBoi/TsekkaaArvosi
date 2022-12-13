package tsekkaa.arvosi;

import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omat_tiedot);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();


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
            editor.putString("nimi", nimi.getText().toString());
            editor.apply();
            //String message = "Käyttäjä: " + nimi;
            //tallenna.putExtra(tallenna.EXTRA_TEXT, message);
            startActivity(tallenna);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
