package tsekkaa.arvosi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class KalenteriActivity extends AppCompatActivity {
    private Button ilmoitusPressed, takaisin;
    private int paiva, kuukausi, vuosi;
    public static final String EXTRA_PVMLISTA = "KalenteriActivity.pvmLista";
    private static LocalDateTime now = LocalDateTime.now();
    private ArrayList<Integer> pvmLista; //Intentissä lähetettävä lista, johon tulee vuosi, kk ja päivä

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalenteri);
        getSupportActionBar().hide();

        //Alustetaan päivämäärä nykyhetkeen - jos käyttäjä ei valitse kalenterista toista päivää, tämä päivä lähetetään intentiin
        paiva = now.getDayOfMonth();
        kuukausi = now.getMonthValue();
        vuosi = now.getYear();

        pvmLista = new ArrayList<>();
        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            //Pitää kirjaa siitä, mikä päivä on valittuna
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                paiva = dayOfMonth;
                kuukausi = month;
                vuosi = year;
            }
        });
    }

    public void setMuistutusPressed(View view) {
        Intent muistutus = new Intent(KalenteriActivity.this, LisaaMuistutusActivity.class);
        pvmLista.add(paiva);
        pvmLista.add(kuukausi);
        pvmLista.add(vuosi);
        muistutus.putExtra(EXTRA_PVMLISTA, pvmLista);
        startActivity(muistutus);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void backButtonPressed(View v) {
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}