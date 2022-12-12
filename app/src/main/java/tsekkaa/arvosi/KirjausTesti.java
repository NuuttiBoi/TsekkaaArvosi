package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class KirjausTesti extends AppCompatActivity {
    public final static String EXTRA_YLAPAINE = "com.example.excercise41.YLAPAINE";
    public final static String EXTRA_ALAPAINE = "com.example.excercise41.ALAPAINE";
    public final static String EXTRA_SYKE = "com.example.excercise41.SYKE";
    public final static String EXTRA_PVM = "com.example.excercise41.PVM";
    public final static String EXTRA_AIKA = "com.example.excercise41.AIKA";
    //Creates edittext
    EditText vsEditText;
    //Creates a button
    Button tallennaButton;
    private MittausViewModel mittausViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirjaus_testi);
        //Finds the edittext from layout and sets it as the created edittext
        this.vsEditText = findViewById(R.id.vsEditText);
        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
        //Finds the button from layout and sets it as the created button
        this.tallennaButton = findViewById(R.id.tallennaVerenPaineButton);
    }

    public void vTallennaButton(View v){

        Calendar calendar = Calendar.getInstance();

        mittausViewModel.lisaaMittaus(new Mittaus(100.0, 100.0, 100.0,
                Double.parseDouble(vsEditText.getText().toString()), 100.0,
                04, 12, 2022, 15, 00, calendar.getTimeInMillis() ));
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
}