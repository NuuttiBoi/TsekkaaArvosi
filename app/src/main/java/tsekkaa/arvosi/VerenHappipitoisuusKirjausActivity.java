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

import java.time.LocalDate;
import java.util.Calendar;

public class VerenHappipitoisuusKirjausActivity extends AppCompatActivity {
    private Button backbutton, tallennaButton, lahetaButton, seurantaButton;
    private TextView kirjausTextView, arvoOhjeTextView, happisaturaatioTextView;
    private EditText happisaturaatioEditText;
    private MittausViewModel mittausViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veren_happipitoisuus);

        this.backbutton = findViewById(R.id.backButton);
        this.tallennaButton = findViewById(R.id.tallennaButton);
        this.seurantaButton = findViewById(R.id.seurantaButton);

        this.kirjausTextView = findViewById(R.id.kirjausTextView);
        this.arvoOhjeTextView = findViewById(R.id.arvoOhjeTextView);
        this.happisaturaatioTextView = findViewById(R.id.happisaturaatioTextView);

        this.happisaturaatioEditText = findViewById(R.id.happisaturaatioEditText);

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
    }


    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
    public void tallennaButtonPressed(View v){

        double happipitoisuus = Double.parseDouble(happisaturaatioEditText.getText().toString());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate aika = LocalDate.now();
        }
        Calendar calendar = Calendar.getInstance();

        // Tallentaa mittauksen
        mittausViewModel.lisaaMittaus(new Mittaus(20.1,10.1, 60.7, 30.1,
                happipitoisuus, 1, 1, 2022, 1,1, calendar.getTimeInMillis()));
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
    public void seurantaButtonPressed(View v){
        Intent seuranta = new Intent(this, VerenHappipitoisuusGraafiActivity.class);
        startActivity(seuranta);
    }
}