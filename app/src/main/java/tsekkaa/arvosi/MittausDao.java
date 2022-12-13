package tsekkaa.arvosi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * A data access object that serves as a handle to access the database.
 * The DAO contains methods for querying, inserting and deleting entries from/into the database
 *
 * @author  Matleena Kankaanpää
 */

@Dao
public interface MittausDao {

    /**
     * A method for inserting a new entry
     * @param mittaus A notification object to be inserted
     */
    @Insert
    void lisaaMittaus(Mittaus mittaus);

    /**
     * A method for retrieving all measurements from the database
     * @return all entries sorted by date and time
     */
    @Query("SELECT * FROM mittaus ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeMittaukset();

    /**
     * A method for retrieving all measurements from the database relevant to tracking systolic blood pressure
     * @return all entries where systolic blood pressure isn't null
     */
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYlapaineMittaukset();

    /** A method for retrieving all measurements from the database relevant to tracking diastolic blood pressure
     * @return all entries where diastolic blood pressure isn't null
     */
    @Query("SELECT * FROM mittaus WHERE alapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeAlapaineMittaukset();

    /** A method for retrieving all measurements from the database relevant to tracking heart rate
     * @return all entries where heart rate isn't null
     */
    @Query("SELECT * FROM mittaus WHERE syke IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeSykeMittaukset();

    /** A method for retrieving all measurements from the database relevant to tracking blood pressure
     * @return all entries where diastolic and systolic blood pressure aren't null
     */
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL AND alapaine IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYpApMittaukset();

    /** A method for retrieving all measurements from the database relevant to tracking diastolic blood pressure and heart rate
     * @return all entries where systolic diastolic blood pressure, diastolic blood pressure and heart rate aren't null
     */
    @Query("SELECT * FROM mittaus WHERE ylapaine IS NOT NULL AND alapaine IS NOT NULL AND syke IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeYpApSykeMittaukset();

    /** A method for retrieving all measurements from the database relevant to tracking blood sugar
     * @return all entries where blood sugar isn't null
     */
    @Query("SELECT * FROM mittaus WHERE verensokeri IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeVerensokeriMittaukset();

    /** A method for retrieving all measurements from the database relevant to tracking blood oxygen
     * @return all entries where diastolic blood oxygen isn't null
     */
    @Query("SELECT * FROM mittaus WHERE happipitoisuus IS NOT NULL ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Mittaus>> haeHappipitoisuusMittaukset();
}