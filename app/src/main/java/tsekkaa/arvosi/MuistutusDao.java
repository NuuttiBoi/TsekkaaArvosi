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
public interface MuistutusDao {

    /**
     * A method for inserting a new entry
     * @param muistutus A notification object to be inserted
     */
    @Insert
    void lisaaMuistutus(Muistutus muistutus);

    /**
     * A method for returning all entries
     * @return all entries in order of creation
     */
    @Query("SELECT * FROM muistutus")
    LiveData<List<Muistutus>> haeMuistutukset();

    /**
     * A method for returning all entries in chronological order
     * @return all entries sorted by date and time
     */
    @Query("SELECT * FROM muistutus ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Muistutus>> haeMuistutuksetKrono();

    /**
     * A method for deleting an entry
     * @param muistutus Selected entry to be deleted
     */
    @Delete
    void poistaMuistutus(Muistutus muistutus);
}