package tsekkaa.arvosi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LisaaMuistutusActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private static LocalDateTime now = LocalDateTime.now();
    private ArrayList<Integer> pvmLista;
    private TextView pvEdit, kkEdit, vvEdit, hhEdit, minEdit;
    private int paiva, kuukausi, vuosi, tunnit, min, valittu_pv, valittu_kk, valittu_vv, valittu_hh, valittu_min;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private LinearLayout pvmContainer, kelloContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_muistutus);
        getSupportActionBar().hide();

        createNotificationChannel();

        Intent intent = getIntent();
        pvmLista = intent.getIntegerArrayListExtra(KalenteriActivity.EXTRA_PVMLISTA);

        pvmContainer = (LinearLayout) findViewById(R.id.pvmContainer);
        kelloContainer = (LinearLayout) findViewById(R.id.kelloContainer);
        pvEdit = (TextView) findViewById(R.id.pvEdit);
        kkEdit = (TextView) findViewById(R.id.kkEdit);
        vvEdit = (TextView) findViewById(R.id.vvEdit);
        hhEdit = (TextView) findViewById(R.id.hhEdit);
        minEdit = (TextView) findViewById(R.id.minEdit);

        //Numerokenttien alkuarvoiksi intentistä saatu päivämäärä ja tämänhetkinen aika
        paiva = pvmLista.get(0);
        kuukausi = pvmLista.get(1);
        vuosi = pvmLista.get(2);
        tunnit = now.getHour();
        min = now.getMinute();

        pvEdit.setText(Integer.toString(paiva));
        kkEdit.setText(Integer.toString(kuukausi));
        vvEdit.setText(Integer.toString(vuosi));
        hhEdit.setText(Integer.toString(tunnit));

        //Lisää alkuun nollan, jos minuutit < 10
        if (min < 10) {
            minEdit.setText("0" + Integer.toString(min));
        } else {
            minEdit.setText(Integer.toString(min));
        }

        //Numerokentät ovat linear layouteissa, joiden klikkaaminen avaa valikon
        //Jokaiseen kenttään päivittyy se arvo, joka on valittuna, kun valikko suljetaan
        pvmContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(LisaaMuistutusActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener,
                        Integer.parseInt((String) vvEdit.getText()),
                        Integer.parseInt((String) kkEdit.getText()),
                        Integer.parseInt((String) pvEdit.getText()));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //OnClickListener linear layoutille, joka näyttää kellonajat
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
                kkEdit.setText(Integer.toString(i1));
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

    public void lisaaMuistutus(View v) {

        //varoitus, jos kentät on tyhjiä tai jos aika mennyt

        //Ottaa tekstikentistä arvot sillä hetkellä, kun "Lisää muistutus" -nappia painetaan
        valittu_pv = Integer.parseInt((String) pvEdit.getText());
        valittu_kk = Integer.parseInt((String) kkEdit.getText());
        valittu_vv = Integer.parseInt((String) vvEdit.getText());
        valittu_hh = Integer.parseInt((String) hhEdit.getText());
        valittu_min = Integer.parseInt((String) minEdit.getText());


        Log.d("", "valittu: " + valittu_pv + "/" + valittu_kk + "/" + valittu_vv
                + " " + valittu_hh + ":" + valittu_min);

        //laske sekunnit

        asetaMuistutus();
    }

    private void asetaMuistutus() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent muistutus = new Intent(this, MuistutusBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, muistutus, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, 10000, pendingIntent);

      /* toistuva
      alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
              AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

       */

        Toast.makeText(this, "Muistutus asetettu", Toast.LENGTH_SHORT).show();
    }

    private void laskeMSekunnit() {

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence kanava = "TsekkaaArvosiMuistutusKanava";
            String kuvaus = "Tsekkaa Arvosi muistutukset";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel muistutusKanava = new NotificationChannel("tsekkaaarvosi", kanava, importance);
            muistutusKanava.setDescription(kuvaus);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(muistutusKanava);
        }
    }

    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, KalenteriActivity.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}