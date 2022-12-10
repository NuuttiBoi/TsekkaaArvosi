package tsekkaa.arvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;

public class VerenSokeriKirjausActivity extends AppCompatActivity {
    //Creates Buttons
    private Button backButton, tallennaButton, kalenteriButton, seurantaButton;
    //Creates TextViews
    private TextView kirjausTextView, verenSokeriTextView;
    //Creates EditText
    private EditText verenSokeriTextEdit;
    //Creates MittausViewModel
    private MittausViewModel mittausViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veren_sokerin_kirjaus);
        //Finds a button from layout with findViewById(R.id.(name of the button)) command and sets the found value to the created button
        this.backButton = findViewById(R.id.backButton);
        this.tallennaButton = findViewById(R.id.tallennaVerenSokeriButton);
        this.seurantaButton = findViewById(R.id.seurantaButton);
        this.kalenteriButton = findViewById(R.id.kalenteriButton);
        //Finds textview from layout with findViewById(R.id.(name of the textview)) command and sets the found value to the created textview
        this.verenSokeriTextView = findViewById(R.id.verenSokeriTextView);
        //Finds TextEdit from layout with findViewById(R.id.(name of the textedit)) command and sets the found value to the created textedit
        this.verenSokeriTextEdit = findViewById(R.id.verenSokeriTextEdit);

        mittausViewModel = new ViewModelProvider(this).get(MittausViewModel.class);
    }

    //Checks if the selected button is pressed and sends the user to the selected page/ activity
    public void backButtonPressed(View v){
        Intent takaisin = new Intent(this, MainActivity.class);
        startActivity(takaisin);
    }
    public void tallennaButtonPressed(View v){

        double verensokeri = Double.parseDouble(verenSokeriTextEdit.getText().toString());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate aika = LocalDate.now();
        }
        Calendar calendar = Calendar.getInstance();

        // Tallentaa mittauksen
        mittausViewModel.lisaaMittaus(new Mittaus(20.1,10.1, 60.7,verensokeri ,
                1.1, 1, 1, 2022, 1,1, calendar.getTimeInMillis()));
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
    public void seurantaButtonPressed(View v){
        Intent seuranta = new Intent(this, VerenSokeriGraafiActivity.class);
        startActivity(seuranta);
    }
}