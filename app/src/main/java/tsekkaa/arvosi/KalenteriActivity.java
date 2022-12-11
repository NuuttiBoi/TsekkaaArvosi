package tsekkaa.arvosi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class KalenteriActivity extends AppCompatActivity {
    private MuistutusViewModel muistutusViewModel;
    private int paiva, kuukausi, vuosi;
    public static final String EXTRA_PVMLISTA = "KalenteriActivity.pvmLista";
    private static LocalDateTime now = LocalDateTime.now();
    private ArrayList<Integer> pvmLista; //Intentissä lähetettävä lista, johon tulee vuosi, kk ja päivä
    private Button lkmButton;

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
        lkmButton = findViewById(R.id.muistutuksetLkm);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                //Pitää kirjaa siitä, mikä päivä on valittuna
                paiva = dayOfMonth;
                kuukausi = month + 1; //Metodi aloittaa kuukausien laskemisen 0:sta, joten lisätään 1
                vuosi = year;
            }
        });

        muistutusViewModel = new ViewModelProvider(this).get(MuistutusViewModel.class);
        muistutusViewModel.haeMuistutukset().observe(this, new Observer<List<Muistutus>>() {
            @Override
            public void onChanged(List<Muistutus> muistutukset) {
                lkmButton.setText(Integer.toString(muistutukset.size()));
            }
        });
    }
    //Checks if the selected button is pressed and sends the user to the selected page/ activity also sends values to the next activity
    public void setMuistutusPressed(View view) {
        Intent muistutus = new Intent(KalenteriActivity.this, LisaaMuistutusActivity.class);
        //adds values to pvmLista
        pvmLista.add(paiva);
        pvmLista.add(kuukausi);
        pvmLista.add(vuosi);
        muistutus.putExtra(EXTRA_PVMLISTA, pvmLista);
        startActivity(muistutus);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void muistutuksetPressed(View view) {
        Intent lkm = new Intent(KalenteriActivity.this, MuistutuksetActivity.class);
        startActivity(lkm);
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);
    }
    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v) {
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}