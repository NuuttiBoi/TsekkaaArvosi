package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import java.util.ArrayList;
import com.google.gson.Gson;

public class TestiActivity2 extends AppCompatActivity {
    private Button btn2;
    private MittausViewModel mittausViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testi2);

        btn2 = findViewById(R.id.btn2);
        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);

        btn2.setOnClickListener(view -> {
            //mittausViewModel.lisaaMittaus(new Mittaus(yläpaine doublena, alapaine doublena, syke doublena, verensokeri doublena, happipitoisuus doublena, päivä intinä, kuukausi intinä, vuosi intinä, tunnit intinä, minuutit intinä));
            Intent intent = new Intent(this, TestiActivity.class);
            startActivity(intent);
        });

    }
}