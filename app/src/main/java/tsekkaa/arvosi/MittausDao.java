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

    //Palauttaa mittaukset joissa yläpaine ei ole null
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYlapaineMittaukset();

    //Palauttaa mittaukset joissa alapaine ei ole null
    @Query("SELECT * FROM mittaus WHERE alapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeAlapaineMittaukset();

    //Palauttaa mittaukset joissa syke ei ole null
    @Query("SELECT * FROM mittaus WHERE syke IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeSykeMittaukset();

    //Palauttaa mittaukset joissa yläpaine ja alapaine ei ole null
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL AND alapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYpApMittaukset();

    //Palauttaa mittaukset joissa yläpaine, alapaine ja syke ei ole null
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL AND alapaine IS NOT NULL AND syke IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYpApSykeMittaukset();

    //Palauttaa mittaukset joissa verensokeri ei ole null
    @Query("SELECT * FROM mittaus WHERE verensokeri IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeVerensokeriMittaukset();

    //Palauttaa mittaukset joissa happipitoisuus ei ole null
    @Query("SELECT * FROM mittaus WHERE happipitoisuus IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeHappipitoisuusMittaukset();

    @Query("DELETE FROM mittaus WHERE id < 15")
    void delete();
}