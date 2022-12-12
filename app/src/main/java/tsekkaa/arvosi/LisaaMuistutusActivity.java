package tsekkaa.arvosi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * An activity for setting a new notification at a specified time, adding a custom message and details,
 * and saving the notification object to the database
 *
 * @author Matleena Kankaanpää
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class LisaaMuistutusActivity extends AppCompatActivity {
    public static final String EXTRA_MITATTAVAT = "LisaaMuistutusActivity.mitattavatLista";
    public static final String EXTRA_LISATIEDOT = "LisaaMuistutusActivity.lisatiedot";
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private static LocalDateTime now = LocalDateTime.now();
    //Creates a list
    private ArrayList<Integer> pvmLista;
    //Creates textviews
    private TextView pvEdit, kkEdit, vvEdit, hhEdit, minEdit;
    //Creates integer variable
    private int paiva, kuukausi, vuosi, tunnit, min, valittu_pv, valittu_kk, valittu_vv, valittu_hh, valittu_min;
    private LinearLayout pvmContainer, kelloContainer;
    //Creates a list
    private ArrayList<String> kuukaudet;
    private ToggleButton verenpaineCheck, sykeCheck, verensokeriCheck, happisaturaatioCheck;
    private CheckBox toistuvaCheck;
    //Creates edittext
    private EditText lisatiedot;
    private MuistutusViewModel muistutusViewModel;
    //Creates strings
    private String mitattavatString, lisatiedotString;
    private int requestCode;
    private Calendar cal;
    private NotificationSetter notificationSetter;
    private long aikaMilliSek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_muistutus);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        pvmLista = intent.getIntegerArrayListExtra(KalenteriActivity.EXTRA_PVMLISTA);

        notificationSetter = new NotificationSetter(this);

        muistutusViewModel = new ViewModelProvider(this).get(MuistutusViewModel.class);
        pvmContainer = findViewById(R.id.pvmContainer);
        kelloContainer = findViewById(R.id.kelloContainer);
        pvEdit = findViewById(R.id.pvEdit);
        kkEdit = findViewById(R.id.kkEdit);
        vvEdit = findViewById(R.id.vvEdit);
        hhEdit = findViewById(R.id.hhEdit);
        minEdit = findViewById(R.id.minEdit);

        //The initial value for number fields comes from the intent that has date and current time
        paiva = pvmLista.get(0);
        kuukausi = pvmLista.get(1); //int 1-12
        vuosi = pvmLista.get(2);
        tunnit = now.getHour();
        min = now.getMinute();
        //Adds all the months to a list
        kuukaudet = new ArrayList<>();
        kuukaudet.add("tammi");
        kuukaudet.add("helmi");
        kuukaudet.add("maalis");
        kuukaudet.add("huhti");
        kuukaudet.add("touko");
        kuukaudet.add("kesä");
        kuukaudet.add("heinä");
        kuukaudet.add("elo");
        kuukaudet.add("syys");
        kuukaudet.add("loka");
        kuukaudet.add("marras");
        kuukaudet.add("joulu");

        //Months were indexed 1-12 in the previous activity, so subtract 1 to adjust to the Date Picker
        kuukausi--;

        cal = Calendar.getInstance();

        pvEdit.setText(Integer.toString(paiva));
        kkEdit.setText(kuukaudet.get(kuukausi));
        vvEdit.setText(Integer.toString(vuosi));
        hhEdit.setText(Integer.toString(tunnit));

        //If minutes < 10, add a leading 0
        if (min < 10) {
            minEdit.setText("0" + Integer.toString(min));
        } else {
            minEdit.setText(Integer.toString(min));
        }

        verenpaineCheck = findViewById(R.id.verenpaineCheck);
        sykeCheck = findViewById(R.id.sykeCheck);
        verensokeriCheck = findViewById(R.id.verensokeriCheck);
        happisaturaatioCheck = findViewById(R.id.happisaturaatioCheck);
        lisatiedot = findViewById(R.id.lisatiedotEdit);
        toistuvaCheck = findViewById(R.id.toistuvaCheck);

        /**
         * OnClickListener set for the whole container which contains the date fields
         * Clicking anywhere within the container opens the date picker, not just clicking on
         * the tiny text boxes
         */
        pvmContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(LisaaMuistutusActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener,
                        Integer.parseInt((String) vvEdit.getText()),
                        kuukaudet.indexOf(kkEdit.getText()),
                        Integer.parseInt((String) pvEdit.getText()));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        /**
         * Same as above but for the time fields
         */
        kelloContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(LisaaMuistutusActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, timeSetListener,
                        Integer.parseInt((String) hhEdit.getText()),
                        Integer.parseInt((String) minEdit.getText()),true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                pvEdit.setText(Integer.toString(i2));
                kkEdit.setText(kuukaudet.get(i1));
                vvEdit.setText(Integer.toString(i));
            }
        };

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hhEdit.setText(Integer.toString(i));
                minEdit.setText(Integer.toString(i1));
            }
        };
    }

    /**
     * Called when the "Add reminder" button is clicked. Checks if the input is valid -
     * if so, calls the functions for creating and saving a new notification object and setting an
     * alarm at the specified time. If the user input is invalid, a warning will appear on the screen.
     */
    public void lisaaMuistutus(View v) {

        /**
         * Gets the current values from the text fields at the time when the button is pressed.
         */
        valittu_pv = Integer.parseInt((String) pvEdit.getText());
        valittu_kk = kuukaudet.indexOf(kkEdit.getText());
        valittu_vv = Integer.parseInt((String) vvEdit.getText());
        valittu_hh = Integer.parseInt((String) hhEdit.getText());
        valittu_min = Integer.parseInt((String) minEdit.getText());

        /**
         * Check for blank fields
         */
        if (tarkistaKentat()) {
            mitattavatString = haeMitattavat();
            lisatiedotString = lisatiedot.getText().toString().trim();

            /**
             * Set calendar values
             * Convert the date to milliseconds and store it in a variable
             */
            cal.set(valittu_vv, valittu_kk, valittu_pv, valittu_hh, valittu_min, 0);
            aikaMilliSek = cal.getTimeInMillis();

            /**
             * Make sure the selected time isn't in the past by comparing it to the current time
             */
            if (aikaMilliSek < System.currentTimeMillis()) {
                Toast.makeText(LisaaMuistutusActivity.this, "Ajankohta on mennyt", Toast.LENGTH_SHORT).show();
            } else {
                if (toistuvaCheck.isChecked()) {
                    //intent
                } else {
                    /**
                     * If the date is valid and no fields are empty, an alarm is set and saved to the database
                     * The date system uses 0-11 indexing for months, so months must be incremented again
                     */
                    tallennaMuistutus(valittu_vv, (valittu_kk+1), valittu_pv, valittu_hh, valittu_min, mitattavatString, lisatiedotString);
                    asetaMuistutus(aikaMilliSek);
                }
            }
        } else {
            Toast.makeText(LisaaMuistutusActivity.this, "Kenttä ei voi olla tyhjä", Toast.LENGTH_SHORT).show();
        }
    }

    //A notification channel is created
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence kanava = "TsekkaaArvosiMuistutusKanava";
            String kuvaus = "Tsekkaa Arvosi muistutukset";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel muistutusKanava = new NotificationChannel("tsekkaa_arvosi", kanava, importance);
            muistutusKanava.setDescription(kuvaus);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(muistutusKanava);
        }
    }

    /*The alarm time and contents are sent to the Alert Receiver in an intent
    After that the user is returned to the previous page */
    private void asetaMuistutus(long aika) {
        /*
         * Get the running request code number from Shared Preferences. A unique request code is needed for
         * distinguishing and managing alarms
         */

        SharedPreferences sharedPref = getSharedPreferences("Muistutukset", Context.MODE_PRIVATE);
        requestCode = sharedPref.getInt("requestCode", 1);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, aika, pendingIntent);

        /*
         * Increment the request code every time a new notification is created
         */
        requestCode++;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("requestCode", requestCode);
        editor.apply();

        /* toistuva
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
              AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
        */
        Log.d("", "alarm req code: " + requestCode);


        Toast.makeText(this, "Muistutus asetettu", Toast.LENGTH_SHORT).show();
        Intent takaisin = new Intent(this, KalenteriActivity.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    //Returns true if at least one field isn't empty
    private boolean tarkistaKentat() {
        if (!verenpaineCheck.isChecked() && !sykeCheck.isChecked() &&
                !verensokeriCheck.isChecked() && !happisaturaatioCheck.isChecked() && lisatiedot.getText().toString().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    //Saves the reminder into the database as a reminder object
    private void tallennaMuistutus(int v, int kk, int pv, int h, int min, String mitattavat, String lisatiedot)  {
        muistutusViewModel.lisaaMuistutus(new Muistutus(pv, kk, v, h, min, mitattavat, lisatiedot));
    }

    /* Returns a list of the selected items in string format
    This string will be passed to the Broadcast Receiver and displayed on the alarm message on the
    user's screen. */
    private String haeMitattavat() {
        boolean vp = verenpaineCheck.isChecked();
        boolean s = sykeCheck.isChecked();
        boolean vs = verensokeriCheck.isChecked();
        boolean vh = happisaturaatioCheck.isChecked();

        String str = "Mittaa ";

        // :--------)
        if (vp && s && vs && vh) {
            str += "verenpaine, syke, verensokeri ja happisaturaatio";
        } else if (vp && s && vs && !vh) {
            str += "verenpaine, syke ja verensokeri";
        } else if (vp && s && !vs && vh) {
            str += "verenpaine, syke ja happisaturaatio";
        } else if (vp && s && !vs && !vh) {
            str += "verenpaine ja syke";
        } else if (vp && !s && vs && vh) {
            str += "verenpaine, verensokeri ja happisaturaatio";
        } else if (vp && !s && vs && !vh) {
            str += "verenpaine ja verensokeri";
        } else if (vp && !s && !vs && vh) {
            str += "verenpaine ja happisaturaatio";
        } else if (vp && !s && !vs && !vh) {
            str += "verenpaine";
        } else if (!vp && s && vs && vh) {
            str += "syke, verensokeri ja happisaturaatio";
        } else if (!vp && s && vs && !vh) {
            str += "syke ja verensokeri";
        } else if (!vp && s && !vs && vh) {
            str += "syke ja happisaturaatio";
        } else if (!vp && s && !vs && !vh) {
            str += "syke";
        } else if (!vp && !s && vs && vh) {
            str += "verensokeri ja happisaturaatio";
        } else if (!vp && !s && vs && !vh) {
            str += "verensokeri";
        } else if (!vp && !s && !vs && vh) {
            str += "happisaturaatio";
        }
        return str;
    }

    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, KalenteriActivity.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}