package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
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

import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author Nuutti Turunen
 */
public class VerenSokeriKirjausActivity extends AppCompatActivity {
    private Button backButton, tallennaButton, kalenteriButton, seurantaButton;
    private TextView kirjausTextView, verenSokeriTextView, aikaOhjeTextView;
    private EditText verenSokeriTextEdit,  pvmEditText, kuukausiEditText, vuosiEditText,
    tunnitEditText, minuutitEditText;;
    //Creates TextViews
    //Creates EditText
    //Creates MittausViewModel
    private MittausViewModel mittausViewModel;
    private TextView mDisplayDate, mDisplayTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veren_sokerin_kirjaus);

        this.pvmEditText = findViewById(R.id.pvmEditTextDate);
        this.kuukausiEditText = findViewById(R.id.kuukausiEditTextDate);
        this.tunnitEditText = findViewById(R.id.tunnitEditTextTime);
        this.minuutitEditText = findViewById(R.id.minuutitEditTextTime);
        this.vuosiEditText = findViewById(R.id.vuosiEditTextDate);
        this.verenSokeriTextEdit = findViewById(R.id.verenSokeriTextEdit);

        //Finds a button from layout with findViewById(R.id.(name of the button)) command and sets the found value to the created button
        this.backButton = findViewById(R.id.backButton);
        this.tallennaButton = findViewById(R.id.tallennaVerenSokeriButton);
        this.seurantaButton = findViewById(R.id.seurantaButton);
        this.kalenteriButton = findViewById(R.id.kalenteriButton);

        this.verenSokeriTextView = findViewById(R.id.verenSokeriTextView);
        //Finds TextEdit from layout with findViewById(R.id.(name of the textedit)) command and sets the found value to the created textedit
        this.verenSokeriTextEdit = findViewById(R.id.verenSokeriTextEdit);
        this.aikaOhjeTextView = findViewById(R.id.aikaOhjeTextView);


        mDisplayDate = (TextView) findViewById(R.id.aikaOhjeTextView);
        mDisplayTime = (TextView) findViewById(R.id.kelloOhjeTextView);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(VerenSokeriKirjausActivity.this,
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

                TimePickerDialog dialog = new TimePickerDialog(VerenSokeriKirjausActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mTimeSetListener, hour,minute,true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);


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

    /**
     * Takes you back to mainactivity
     * @param v Button that is pressed
     */
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
    public void tallennaButtonPressed(View v){

        if((pvmEditText.getText().toString().trim().length() < 1) ||
                (verenSokeriTextEdit.getText().toString().trim().length() < 1) ||
                (kuukausiEditText.getText().toString().trim().length() < 1) ||
                (vuosiEditText.getText().toString().trim().length() < 1) ||
                (tunnitEditText.getText().toString().trim().length() < 1) ||
                (minuutitEditText.getText().toString().trim().length() < 1) ){
            Toast.makeText(getApplicationContext(),"Tarkista, että et ole jättänyt tyhjiä kenttiä!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate aika = LocalDate.now();
        }
        Calendar calendar = Calendar.getInstance();

        double verensokeri = Double.parseDouble(verenSokeriTextEdit.getText().toString());
        int paiva = Integer.parseInt(pvmEditText.getText().toString());
        int kuukausi = Integer.parseInt(kuukausiEditText.getText().toString());
        int tunnit = Integer.parseInt(tunnitEditText.getText().toString());
        int minuutit = Integer.parseInt(minuutitEditText.getText().toString());
        int vuosi = Integer.parseInt(vuosiEditText.getText().toString());


        // Tallentaa mittauksen
        mittausViewModel.lisaaMittaus(new Mittaus(null,null, null,verensokeri ,
                null, paiva, kuukausi, vuosi, tunnit,minuutit, calendar.getTimeInMillis()));
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
    /**
     * Takes you to verensokerigraafiactivity
     * @param v Button that is pressed
     */
    public void seurantaButtonPressed(View v){
        Intent seuranta = new Intent(this, VerenSokeriGraafiActivity.class);
        startActivity(seuranta);
    }
}