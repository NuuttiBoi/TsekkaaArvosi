package tsekkaa.arvosi;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Asetukset extends AppCompatActivity {
    private Button kirjauduUlos;

    public Asetukset() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asetukset);

        this.kirjauduUlos = findViewById(R.id.kirjauduUlosButton);
    }

    /**public void kirjauduUlosButtonpressed(View asetusnakyma){
        Intent intentKirjauduUlos = new Intent(this, MessageActivity.class);
        startActivity(intentKirjauduUlos); teksti Olet kirjautunut ulos sovelluksesta
    }**/
}


