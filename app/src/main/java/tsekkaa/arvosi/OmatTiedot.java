package tsekkaa.arvosi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class OmatTiedot extends AppCompatActivity {
    private EditText nimi, ika;
    private Button tallenna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omat_tiedot);

        this.nimi = findViewById(R.id.nimiEditText);
        this.ika = findViewById(R.id.ikaEditText);
        this.tallenna = findViewById(R.id.otTallennaButton);

        public void otTallennaButtonPressed(View v){
            Intent tallenna = new Intent(this, Asetukset.class);
            String message = "Käyttäjä: " + nimi;
            tallenna.putExtra(tallenna.EXTRA_TEXT, message);
            startActivity(tallenna);
        }
    }
}
