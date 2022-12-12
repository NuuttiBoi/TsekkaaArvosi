package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;

public class ArvojenSeurantaActivity extends AppCompatActivity {

    private Button sykeButton, alapaineButton, ylapaineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvojen_seuranta);

        this.sykeButton = findViewById(R.id.sykeButton);
        this.alapaineButton = findViewById(R.id.alapaineButton);
        this.ylapaineButton = findViewById(R.id.ylapaineButton);

    }

    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void sykeButton(View v){
        Intent aLaheta = new Intent(this, SykeGraafiActivity.class);
        startActivity(aLaheta);
    }
}