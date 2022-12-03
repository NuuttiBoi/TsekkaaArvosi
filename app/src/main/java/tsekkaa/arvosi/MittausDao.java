package tsekkaa.arvosi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Data access object

@Dao
public interface MittausDao {

    @Insert
    void lisaaMittaus(Mittaus mittaus);

    //Palauttaa mittaukset aikajärjestyksessä
    @Query("SELECT * FROM mittaus ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeMittaukset();
}