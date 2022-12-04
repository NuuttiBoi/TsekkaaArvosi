package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class VerenSokeriActivity extends AppCompatActivity {
    private Button tallenna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veren_sokerin_kirjaus);

        this.tallenna = findViewById(R.id.tallennusButton);
    }
}