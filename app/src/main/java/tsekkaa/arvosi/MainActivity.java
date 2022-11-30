package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button verenpaineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.verenpaineButton =findViewById(R.id.verenpaineButton);
    }
    public void vPaineButtonPressed(View v){
        Intent vPaine = new Intent(this, VerenpaineKirjausActivity.class);
        startActivity(vPaine);
    }

}