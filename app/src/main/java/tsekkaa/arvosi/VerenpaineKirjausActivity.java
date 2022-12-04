package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    TextView kirjausTextView, alaPaineTextView, ylaPaineTextView, sykeTextView, arvoOhjeTextView, aikaOhjeTextView;
    EditText ylaPaineEditText, alaPaineEditText, sykeEditText, pvmEditText, aikaEditText;
    Button tallennaButton, lahetaButton;
    private MittausViewModel mittausViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verenpaine_kirjaus);

        this.kirjausTextView = findViewById(R.id.kirjausTextView);
        this.alaPaineTextView = findViewById(R.id.alaPaineTextView);
        this.ylaPaineTextView = findViewById(R.id.ylaPaineTextView);
        this.sykeTextView = findViewById(R.id.sykeTextView);
        this.arvoOhjeTextView = findViewById(R.id.arvoOhjeTextView);
        this.aikaOhjeTextView = findViewById(R.id.aikaOhjeTextView);

        this.ylaPaineEditText = findViewById(R.id.ylaPaineEditText);
        this.alaPaineEditText = findViewById(R.id.alaPaineEditText);
        this.sykeEditText = findViewById(R.id.sykeEditText);
        this.pvmEditText = findViewById(R.id.pvmEditTextDate);
        this.aikaEditText = findViewById(R.id.aikaEditTextTime);

        this.tallennaButton = findViewById(R.id.tallennaButton);
        this.lahetaButton = findViewById(R.id.lahetaButton);

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
    }



    // Vie verenpaineen tarkkailu activityyn kun käyttäjä painaa nappia
    public void vTallennaButton(View v){

        double ylaPaine = Double.parseDouble(ylaPaineEditText.getText().toString());
        double alaPaine = Double.parseDouble(alaPaineEditText.getText().toString());
        double syke = Double.parseDouble(sykeEditText.getText().toString());

        mittausViewModel.lisaaMittaus(new Mittaus(ylaPaine,alaPaine, syke, 100.0,
                04, 12, 2022, 15, 00,00));
        Log.d("", "tallenna");


        mittausViewModel.haeMittaukset().observe(this, mittaukset -> {
            for (int i = 0; i < mittaukset.size(); i++) {
                Log.d("", "yp: " + mittaukset.get(i).getYlapaine()
                        + ", ap: " + mittaukset.get(i).getAlapaine()
                        + ", syke: " + mittaukset.get(i).getSyke()
                        + ", vsok: " + mittaukset.get(i).getVerensokeri()
                        + ", vhappi: " + mittaukset.get(i).getHappipitoisuus()
                        + " " + mittaukset.get(i).getPaiva() + "." + mittaukset.get(i).getKuukausi() + "." + mittaukset.get(i).getVuosi()
                        + " " + mittaukset.get(i).getTunnit() + ":" + mittaukset.get(i).getMinuutit());
            }
        });
    }


    /*
    public void aLahetaButton(View v){
        Intent aLaheta = new Intent(this, Mittaus.class);
        String ylaPaine = ylaPaineEditText.getText().toString();
        String alaPaine = alaPaineEditText.getText().toString();
        String syke = sykeEditText.getText().toString();

        String pvm = pvmEditText.getText().toString();
        String aika = aikaEditText.getText().toString();

        aLaheta.putExtra(EXTRA_YLAPAINE, ylaPaine);
        aLaheta.putExtra(EXTRA_ALAPAINE, alaPaine);
        aLaheta.putExtra(EXTRA_SYKE, syke);
    }
     */
}