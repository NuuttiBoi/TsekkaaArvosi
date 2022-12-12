package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * An activity which displays notifications set by the user in the style of a dropdown menu
 * The notification items are arranged in a RecyclerView. Swiping left deletes a notification
 *
 * @author  Matleena Kankaanpää
 * @version 1.0
 * @since   2022-12-14
 * @see     <a href="https://www.youtube.com/watch?v=QJUCD32dzHE>Tutorial by Coding in Flow on YouTube</a>
 */

public class MuistutuksetActivity extends AppCompatActivity {
    public static final String EXTRA_MITATTAVAT = "LisaaMuistutusActivity.mitattavatLista";
    public static final String EXTRA_LISATIEDOT = "LisaaMuistutusActivity.lisatiedot";
    private MuistutusViewModel muistutusViewModel;
    private int requestCode;
    private String mitattavat, lisatiedot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muistutukset);
        getSupportActionBar().hide();

        RecyclerView recView = findViewById(R.id.muistutusRecView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setHasFixedSize(true);

        final MuistutusAdapter adapter = new MuistutusAdapter();
        recView.setAdapter(adapter);

        muistutusViewModel = new ViewModelProvider(this).get(MuistutusViewModel.class);

        muistutusViewModel.haeMuistutuksetKrono().observe(this, new Observer<List<Muistutus>>() {
            @Override
            public void onChanged(List<Muistutus> muistutukset) {
                /**
                 * Updates the view to the adapter
                 */
                adapter.naytaMuistutukset(muistutukset);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            /**
             * The ItemTouchHelper detects movement and acts accordingly
             * The OnMove function is for drag-and-dropping, but since only swiping is implemented,
             * it is left empty
             */
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            /**
             * Deletes a notification when the user swipes on it
             * @param viewHolder A notification item in the Recycler View
             * @param direction
             */
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //Delete the notitication through the View Model
                muistutusViewModel.poistaMuistutus(adapter.haePaikka(viewHolder.getAdapterPosition()));

                /*
                The notification has been erased from the database, but the alarm popup still has to be canceled
                separately using Alarm Manager
                An exact copy of the deleted alarm needs to be created in order to override and cancel it, therefore
                we're grabbing the needed attributes of the currently selected notification which will be
                passed to the cancellation function
                These are the same attributes which were originally included in the intent which was
                passed to the Alert Receiver
                 */
                requestCode = adapter.haePaikka((viewHolder.getAdapterPosition())).getRequestCode();
                mitattavat = adapter.haePaikka((viewHolder.getAdapterPosition())).getMitattavat();
                lisatiedot = adapter.haePaikka((viewHolder.getAdapterPosition())).getLisatiedot();

                //Call the alarm cancellation function
                peruMuistutus(requestCode, mitattavat, lisatiedot);

                Toast.makeText(MuistutuksetActivity.this, "Muistutus poistettu", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recView);
    }

    /**
     * "Recreates" an exact copy of alarm to be deleted, then deletes it. The request code
     * becomes important here because the newly created alarm will override the pending alarm with
     * the same code. The new alarm is then canceled, thus no alarm will go off
     */
    private void peruMuistutus(int requestCode, String mitattavat, String lisatiedot) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra(EXTRA_MITATTAVAT, mitattavat);
        intent.putExtra(EXTRA_LISATIEDOT, lisatiedot);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    /**
     * A function to exit the activity
     * @param v The "close" button which was clicked
     */
    public void suljeButtonPressed(View v){
        Intent sulje = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sulje = new Intent(this, KalenteriActivity.class);
        }
        startActivity(sulje);
        overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
    }
}