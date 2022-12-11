package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ToistuvaMuistutusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toistuva_muistutus);
    }

    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            takaisin = new Intent(this, LisaaMuistutusActivity.class);
        }
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        //tallenna tiedot
    }
}