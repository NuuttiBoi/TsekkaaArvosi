package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class VerenpaineKirjausActivity extends AppCompatActivity {

    private static final String TAG = "VerenPaineKirjausActivity";

    public final static String EXTRA_YLAPAINE = "com.example.excercise41.YLAPAINE";
    public final static String EXTRA_ALAPAINE = "com.example.excercise41.ALAPAINE";
    public final static String EXTRA_SYKE = "com.example.excercise41.SYKE";
    public final static String EXTRA_PVM = "com.example.excercise41.PVM";
    public final static String EXTRA_AIKA = "com.example.excercise41.AIKA";
    TextView kirjausTextView, alaPaineTextView, ylaPaineTextView, sykeTextView, arvoOhjeTextView, aikaOhjeTextView, pisteTextView;
    EditText ylaPaineEditText, alaPaineEditText, sykeEditText, pvmEditText, kuukausiEditText, vuosiEditText, tunnitEditText, minuutitEditText;
    Button tallennaButton, lahetaButton, backbutton;
    private MittausViewModel mittausViewModel;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verenpaine_kirjaus);

        mDisplayDate = (TextView) findViewById(R.id.aikaOhjeTextView);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(VerenpaineKirjausActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });



        /*
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText(currentDate);

         */

        this.kirjausTextView = findViewById(R.id.kirjausTextView);
        this.alaPaineTextView = findViewById(R.id.alaPaineTextView);
        this.ylaPaineTextView = findViewById(R.id.ylaPaineTextView);
        this.sykeTextView = findViewById(R.id.sykeTextView);
        this.arvoOhjeTextView = findViewById(R.id.arvoOhjeTextView);
        this.aikaOhjeTextView = findViewById(R.id.aikaOhjeTextView);
        this.pisteTextView = findViewById(R.id.pisteTextView);


        this.ylaPaineEditText = findViewById(R.id.ylaPaineEditText);
        this.alaPaineEditText = findViewById(R.id.alaPaineEditText);
        this.sykeEditText = findViewById(R.id.sykeEditText);
        this.pvmEditText = findViewById(R.id.pvmEditTextDate);
        this.kuukausiEditText = findViewById(R.id.kuukausiEditTextDate);
        this.tunnitEditText = findViewById(R.id.tunnitEditTextTime);
        this.minuutitEditText = findViewById(R.id.minuutitEditTextTime);
        this.vuosiEditText = findViewById(R.id.vuosiEditTextDate);

        this.tallennaButton = findViewById(R.id.tallennaButton);
        this.lahetaButton = findViewById(R.id.lahetaButton);
        this.backbutton = findViewById(R.id.backButton);

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                pvmEditText.setText(Integer.toString(i2));
                kuukausiEditText.setText(Integer.toString(i1));
                vuosiEditText.setText(Integer.toString(i));

            }
        };

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
    }



    // Vie verenpaineen tarkkailu activityyn kun käyttäjä painaa nappia
    public void vTallennaButton(View v){

        double ylaPaine = Double.parseDouble(ylaPaineEditText.getText().toString());
        double alaPaine = Double.parseDouble(alaPaineEditText.getText().toString());
        double syke = Double.parseDouble(sykeEditText.getText().toString());
        int paiva = Integer.parseInt(pvmEditText.getText().toString());
        int kuukausi = Integer.parseInt(kuukausiEditText.getText().toString());
        int tunnit = Integer.parseInt(tunnitEditText.getText().toString());
        int minuutit = Integer.parseInt(minuutitEditText.getText().toString());

        mittausViewModel.lisaaMittaus(new Mittaus(ylaPaine,alaPaine, syke, 100.0,
                04, paiva, kuukausi, 2022, tunnit,minuutit));
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



    public void aLahetaButton(View v){
        Intent aLaheta = new Intent(this, Mittaus.class);
        String ylaPaine = ylaPaineEditText.getText().toString();
        String alaPaine = alaPaineEditText.getText().toString();
        String syke = sykeEditText.getText().toString();

        String pvm = pvmEditText.getText().toString();
        String kuukausi = kuukausiEditText.getText().toString();

        aLaheta.putExtra(EXTRA_YLAPAINE, ylaPaine);
        aLaheta.putExtra(EXTRA_ALAPAINE, alaPaine);
        aLaheta.putExtra(EXTRA_SYKE, syke);
    }

    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
}