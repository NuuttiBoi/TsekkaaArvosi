package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Takers user input blood pressure and hearbeat measurement values and saves them in to the database.
 * @author Nuutti Turunen
 */
public class VerenpaineKirjausActivity extends AppCompatActivity {

    private static final String TAG = "VerenPaineKirjausActivity";

    public final static String EXTRA_YLAPAINE = "com.example.excercise41.YLAPAINE";
    public final static String EXTRA_ALAPAINE = "com.example.excercise41.ALAPAINE";
    public final static String EXTRA_SYKE = "com.example.excercise41.SYKE";
    public final static String EXTRA_DATE = "com.example.excercise41.DATE";
    public final static String EXTRA_AIKA = "com.example.excercise41.AIKA";
    public final static String EXTRA_RESTART = "com.example.tsekkaa.arvosi.RESTART";

    TextView kirjausTextView, alaPaineTextView, ylaPaineTextView, sykeTextView, arvoOhjeTextView, aikaOhjeTextView,
            kelloOhjeTextView, pisteTextView;
    EditText ylaPaineEditText, alaPaineEditText, sykeEditText, pvmEditText, kuukausiEditText, vuosiEditText,
            tunnitEditText, minuutitEditText;
    Button tallennaButton, lahetaButton, backbutton;
    private MittausViewModel mittausViewModel;
    private TextView mDisplayDate, mDisplayTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private int paiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verenpaine_kirjaus);
        getSupportActionBar().hide();

        mDisplayDate = (TextView) findViewById(R.id.aikaOhjeTextView);
        mDisplayTime = (TextView) findViewById(R.id.kelloOhjeTextView);

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
        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(VerenpaineKirjausActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mTimeSetListener, hour,minute,true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        this.kirjausTextView = findViewById(R.id.kirjausTextView);
        this.alaPaineTextView = findViewById(R.id.alaPaineTextView);
        this.ylaPaineTextView = findViewById(R.id.happisaturaatioTextView);
        this.sykeTextView = findViewById(R.id.sykeTextView);
        this.arvoOhjeTextView = findViewById(R.id.arvoOhjeTextView);
        this.aikaOhjeTextView = findViewById(R.id.aikaOhjeTextView);
        this.kelloOhjeTextView = findViewById(R.id.kelloOhjeTextView);
        this.pisteTextView = findViewById(R.id.pisteTextView);


        this.ylaPaineEditText = findViewById(R.id.happisaturaatioEditText);
        this.alaPaineEditText = findViewById(R.id.alaPaineEditText);
        this.sykeEditText = findViewById(R.id.sykeEditText);
        this.pvmEditText = findViewById(R.id.pvmEditTextDate);
        this.kuukausiEditText = findViewById(R.id.kuukausiEditTextDate);
        this.tunnitEditText = findViewById(R.id.tunnitEditTextTime);
        this.minuutitEditText = findViewById(R.id.minuutitEditTextTime);
        this.vuosiEditText = findViewById(R.id.vuosiEditTextDate);

        this.tallennaButton = findViewById(R.id.tallennaVerenPaineButton);
        this.lahetaButton = findViewById(R.id.seurantaButton);
        this.backbutton = findViewById(R.id.backButton);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                i1 = i1 + 1;

                pvmEditText.setText(Integer.toString(i2));
                kuukausiEditText.setText(Integer.toString(i1));
                vuosiEditText.setText(Integer.toString(i));

                String date = i2 + "/" + i1 + "/" + i;
            }
        };

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {


            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                tunnitEditText.setText(Integer.toString(i));
                minuutitEditText.setText(Integer.toString(i1));

            }
        };

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
    }

    // Takes you to blood pressure monitoring activity when user presses the button
    public void vpTallennaButton(View v){

        if((ylaPaineEditText.getText().toString().trim().length() < 1) ||
                (alaPaineEditText.getText().toString().trim().length() < 1) ||
                (sykeEditText.getText().toString().trim().length() < 1) ||
                (pvmEditText.getText().toString().trim().length() < 1) ||
                (kuukausiEditText.getText().toString().trim().length() < 1) ||
                (vuosiEditText.getText().toString().trim().length() < 1) ||
                (tunnitEditText.getText().toString().trim().length() < 1) ||
                (minuutitEditText.getText().toString().trim().length() < 1) ){
            Toast.makeText(getApplicationContext(),"Tarkista, että et ole jättänyt tyhjiä kenttiä!",Toast.LENGTH_SHORT).show();
            return;
        }

        double ylaPaine = Double.parseDouble(ylaPaineEditText.getText().toString());

        double alaPaine = Double.parseDouble(alaPaineEditText.getText().toString());
        double syke = Double.parseDouble(sykeEditText.getText().toString());
        this.paiva = Integer.parseInt(pvmEditText.getText().toString());
        int kuukausi = Integer.parseInt(kuukausiEditText.getText().toString());
        int vuosi = Integer.parseInt(vuosiEditText.getText().toString());
        int tunnit = Integer.parseInt(tunnitEditText.getText().toString());
        int minuutit = Integer.parseInt(minuutitEditText.getText().toString());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate aika = LocalDate.now();
        }

        Calendar calendar = Calendar.getInstance();

        if((ylaPaine > 40 && ylaPaine < 200) && (alaPaine > 40 && alaPaine < 200) && (syke > 20 && syke < 200) &&
                (paiva > 0 && paiva <= 31) && (kuukausi > 0 && kuukausi <=12)){

            mittausViewModel.lisaaMittaus(new Mittaus(ylaPaine,alaPaine, syke, null,
                    null, paiva, kuukausi, vuosi, tunnit,minuutit, calendar.getTimeInMillis()));

        } else {
            Toast.makeText(getApplicationContext(),"Varmista, että syöttämäsi arvot ovat" +
                    " oikeita!",Toast.LENGTH_SHORT).show(); /* If the user inputs unrealistic values, they will be prompted
                                                               to check that they are correct. */
        }

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

    /**
     * Takes you back to arvojenseurantaactivity
     * @param v Button that is pressed
     */
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void aLahetaButton(View v){
        Intent aLaheta = new Intent(this, ArvojenSeurantaActivity.class);
        startActivity(aLaheta);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
    /**
     * Takes you back to VerenpaineKirjausActivity
     * @param v Button that is pressed
     */
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}