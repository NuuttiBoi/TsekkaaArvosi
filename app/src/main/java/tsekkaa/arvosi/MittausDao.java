package tsekkaa.arvosi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * The database isn't referenced directly from the activity
 * Instead, a data access object serves as a handle to access it
 * The DAO contains methods for querying, inserting and deleting entries from/into the database
 *
 * @author Matleena Kankaanpää
 */

@Dao
public interface MittausDao {

    /**
     * Insert a new entry
     */
    @Insert
    void lisaaMittaus(Mittaus mittaus);

    /**
     * @return all entries sorted by date and time
     */
    @Query("SELECT * FROM mittaus ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeMittaukset();

    /**
     * @return all entries where systolic blood pressure isn't null
     */
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYlapaineMittaukset();

    /**
     * @return all entries where diastolic blood pressure isn't null
     */    @Query("SELECT * FROM mittaus WHERE alapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeAlapaineMittaukset();

    /**
     * @return all entries where heartbeat isn't null
     */
    @Query("SELECT * FROM mittaus WHERE syke IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeSykeMittaukset();

    /**
     * @return all entries where neither systolic nor diastolic blood pressure is null
     */
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL AND alapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYpApMittaukset();

    /**
     * @return all entries where systolic and diastolic blood pressure and heartbeat aren't null
     */
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL AND alapaine IS NOT NULL AND syke IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYpApSykeMittaukset();

    /**
     * @return all entries where blood sugar isn't null
     */
    @Query("SELECT * FROM mittaus WHERE verensokeri IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeVerensokeriMittaukset();

    /**
     * @return all entries where blood oxygen level isn't null
     */
    @Query("SELECT * FROM mittaus WHERE happipitoisuus IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeHappipitoisuusMittaukset();

    /**
     * Delete an entry
     * @param mittaus Selected entry to be deleted
     */
    @Delete
    void poistaMittaus(Mittaus mittaus);
}