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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omat_tiedot);
        getSupportActionBar().hide();

        nimi = findViewById(R.id.nimiEditText);
        ika = findViewById(R.id.ikaEditText);
        tallenna = findViewById(R.id.otTallennaButton);
        }

    /**
     * Returns user to the settings activity
     * @param v Button that is pressed
     */
    public void otTallennaButtonPressed(View v){
        Intent tallenna = new Intent(this, Asetukset.class);
            SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            String kayttajanNimi = nimi.getText().toString();
            editor.putString("nimi", kayttajanNimi);
            editor.apply();

            startActivity(tallenna);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
