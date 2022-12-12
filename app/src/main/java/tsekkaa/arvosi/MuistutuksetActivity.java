package tsekkaa.arvosi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
 * Done with the help of a tutorial by Coding in Flow on YouTube
 *
 * https://www.youtube.com/watch?v=QJUCD32dzHE
 *
 * @author Matleena Kankaanpää
 */

public class MuistutuksetActivity extends AppCompatActivity {
    private MuistutusViewModel muistutusViewModel;

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
                muistutusViewModel.poistaMuistutus(adapter.haePaikka(viewHolder.getAdapterPosition()));

                Toast.makeText(MuistutuksetActivity.this, "Muistutus poistettu", Toast.LENGTH_SHORT).show();

                muistutusViewModel.haeMuistutukset().observe(MuistutuksetActivity.this, muistutukset -> {
                    Log.d("", "muistutusten lkm: " + muistutukset.size());
                    //poista hälytys
                });
            }
        }).attachToRecyclerView(recView);
    }


    public void suljeButtonPressed(View v){
       muistutusViewModel.haeMuistutukset().observe(this, muistutukset -> {
           Log.d("", "" + muistutukset.size());
       });

        Intent sulje = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            sulje = new Intent(this, KalenteriActivity.class);
        }
        startActivity(sulje);
        overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
    }
}