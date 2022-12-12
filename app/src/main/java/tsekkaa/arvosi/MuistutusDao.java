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
public interface MuistutusDao {

    /**
     * Insert a new entry
     * @param muistutus A notification object to be inserted
     */
    @Insert
    void lisaaMuistutus(Muistutus muistutus);

    /**
     * @return all entries in the order of creation
     */
    @Query("SELECT * FROM muistutus")
    LiveData<List<Muistutus>> haeMuistutukset();

    /**
     * @return all entries sorted by date and time
     */
    @Query("SELECT * FROM muistutus ORDER BY vuosi, kuukausi, paiva, tunnit, minuutit")
    LiveData<List<Muistutus>> haeMuistutuksetKrono();

    /**
     * Delete an entry
     * @param muistutus Selected entry to be deleted
     */
    @Delete
    void poistaMuistutus(Muistutus muistutus);
}