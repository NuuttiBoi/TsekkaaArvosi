package tsekkaa.arvosi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//Data access object

@Dao
public interface MuistutusDao {

    @Insert
    void lisaaMuistutus(Muistutus muistutus);

    //Palauttaa muistutukset lisäämisjärjestyksessä
    @Query("SELECT * FROM muistutus")
    LiveData<List<Muistutus>> haeMuistutukset();

    //Palauttaa muistutukset aikajärjestyksessä
    @Query("SELECT * FROM muistutus ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Muistutus>> haeMuistutuksetKrono();

    @Delete
    void poistaMuistutus(Muistutus muistutus);
}