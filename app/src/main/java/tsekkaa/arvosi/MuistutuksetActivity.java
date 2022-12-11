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
        muistutusViewModel.haeMuistutukset().observe(this, new Observer<List<Muistutus>>() {
            @Override
            public void onChanged(List<Muistutus> muistutukset) {
                //Updates the view
                adapter.naytaMuistutukset(muistutukset);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                muistutusViewModel.poistaMuistutus(adapter.haePaikka(viewHolder.getAdapterPosition()));

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