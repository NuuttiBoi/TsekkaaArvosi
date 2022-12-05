package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerenSokeriActivity extends AppCompatActivity {
    //luo buttonin
    private Button tallennabutton, backbutton, siirrybutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veren_sokerin_kirjaus);

        this.tallennabutton = findViewById(R.id.tallennusButton);
        this.backbutton = findViewById(R.id.Backbutton);
        this.siirrybutton = findViewById(R.id.siirryKalenteriinbutton);
    }
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
}