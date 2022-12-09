package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//Riku Nokelainen

public class VerenSokeriKirjausActivity extends AppCompatActivity {
    private Button tallennabutton, backbutton, siirrybutton;
    public static final String EXTRA_VERENSOKERIARVO = "verensokeriArvo";
    private EditText verensokeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veren_sokerin_kirjaus);

        //Finds a button from layout with findViewById(R.id.(wanted thing)) command and sets the found value to the created button
        this.tallennabutton = findViewById(R.id.tallennusButton);
        this.backbutton = findViewById(R.id.Backbutton);
        this.siirrybutton = findViewById(R.id.siirryKalenteriinbutton);
        this.verensokeri = findViewById(R.id.verensokerinArvo);
    }



    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
}