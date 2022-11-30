package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class VerenpaineKirjausActivity extends AppCompatActivity {
    public final static String EXTRA_YLAPAINE = "com.example.excercise41.YLAPAINE";
    public final static String EXTRA_ALAPAINE = "com.example.excercise41.ALAPAINE";
    public final static String EXTRA_SYKE = "com.example.excercise41.SYKE";
    public final static String EXTRA_PVM = "com.example.excercise41.PVM";
    public final static String EXTRA_AIKA = "com.example.excercise41.AIKA";
    TextView kirjausTextView, alaPaineTextView, ylaPaineTextView, sykeTextView;
    EditText ylaPaineEditText, alaPaineEditText, sykeEditText, pvmEditText, aikaEditText;
    Button tallennaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verenpaine_kirjaus);

        this.kirjausTextView = findViewById(R.id.kirjausTextView);
        this.alaPaineTextView = findViewById(R.id.alaPaineTextView);
        this.ylaPaineTextView = findViewById(R.id.ylaPaineTextView);
        this.sykeTextView = findViewById(R.id.sykeTextView);

        this.ylaPaineEditText = findViewById(R.id.ylaPaineEditText);
        this.alaPaineEditText = findViewById(R.id.alaPaineEditText);
        this.sykeEditText = findViewById(R.id.sykeEditText);
        this.pvmEditText = findViewById(R.id.pvmEditTextDate);
        this.aikaEditText = findViewById(R.id.aikaEditTextTime);

        this.tallennaButton = findViewById(R.id.tallennaButton);
    }
    public void vTallennaButton(View v){
        Intent Arvot = new Intent(this, VerenpaineTarkkailuActivity.class);
    }
}