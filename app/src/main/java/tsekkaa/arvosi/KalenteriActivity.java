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

/**
 * An activity which displays a calendar. The user can pick a date to set a reminder.
 *
 * @author  Matleena Kankaanpää
 * @version 1.0
 * @since   2022-12-14
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class KalenteriActivity extends AppCompatActivity {
    /**
     * A key for intent extras
     */
    public static final String EXTRA_PVMLISTA = "KalenteriActivity.pvmLista";

    private MuistutusViewModel muistutusViewModel;
    private int paiva, kuukausi, vuosi;
    private static LocalDateTime now = LocalDateTime.now();

    //A list containing the selected year, month and day, to be sent to the next activity in an intent
    private ArrayList<Integer> pvmLista;
    private Button lkmButton;
    private long aikaNyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalenteri);
        getSupportActionBar().hide();

        /*
        Gets the current date, which will be sent to the next activity as default if the user
        doesn't select another date from the calendar
         */
        paiva = now.getDayOfMonth();
        kuukausi = now.getMonthValue();
        vuosi = now.getYear();

        pvmLista = new ArrayList<>();
        CalendarView cal = findViewById(R.id.calendarView);
        lkmButton = findViewById(R.id.muistutuksetLkm);
        aikaNyt = System.currentTimeMillis();

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                //Keeps track of what day is selected
                paiva = dayOfMonth;
                kuukausi = month + 1; //Method starts the counting of months from 0 so lets add 1
                vuosi = year;
            }
        });

        muistutusViewModel = new ViewModelProvider(this).get(MuistutusViewModel.class);

        /*
         * The red circle button shows only upcoming alarms. Once the alarm gone off, the popup
         * message will remain on the user's screen until it's clicked off, but expired alarms
         * are automatically deleted from the database to not take up storage
         */
        muistutusViewModel.haeMuistutukset().observe(this, muistutukset -> {
            for (int i = 0; i < muistutukset.size(); i++) {
                if (muistutukset.get(i).AikaMilliSek() < System.currentTimeMillis()) {
                    muistutusViewModel.poistaMuistutus(muistutukset.get(i));
                }
            }
        });

        /*
         * Displays the current amount of upcoming alarms on the circle icon
         */
        muistutusViewModel.haeMuistutukset().observe(this, new Observer<List<Muistutus>>() {
            @Override
            public void onChanged(List<Muistutus> muistutukset) {
                lkmButton.setText(Integer.toString(muistutukset.size()));
            }
        });
    }

    /**
     * Sends the user to the activity where the reminder can be created, along with the date
     * selected from the calendar (current date by default)
     * @param view The button that was pressed
     */
    public void setMuistutusPressed(View view) {
        Intent muistutus = new Intent(KalenteriActivity.this, LisaaMuistutusActivity.class);
        pvmLista.add(paiva);
        pvmLista.add(kuukausi);
        pvmLista.add(vuosi);
        muistutus.putExtra(EXTRA_PVMLISTA, pvmLista);
        startActivity(muistutus);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    /**
     * Opens the activity which displays current upcoming alarms, with a slide down animation
     * @param view The button that was pressed
     */
    public void muistutuksetPressed(View view) {
        Intent lkm = new Intent(KalenteriActivity.this, MuistutuksetActivity.class);
        startActivity(lkm);
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);
    }

    /**
     * Returns the user to Main Activity
     * @param view The button that was pressed
     */
    public void backButtonPressed(View view) {
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}