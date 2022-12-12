package tsekkaa.arvosi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * A Room database which stores measurement and reminder objects.
 * The version number is incremented each time the database is updated
 * Done with the help of tutorials by Coding in Flow on YouTube
 *
 * https://www.youtube.com/watch?v=ARpn-1FPNE4&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118
 */

@Database(entities = {Mittaus.class, Muistutus.class}, version = 7)
public abstract class MittausTietokanta extends RoomDatabase {

    /**
     * Separate data access objects for both kinds of records
     */
    public abstract MittausDao mittausDao();
    public abstract MuistutusDao muistutusDao();

    /**
     * The Singleton pattern is used to create a single instance of the database
     */
    public static MittausTietokanta INSTANCE;

    /**
     * Synchronization prevents multiple threads from creating the singleton object simultaneously
     */
    public static MittausTietokanta getInstance(final Context context) {
        if (INSTANCE == null) {
            /**
             * Only one thread can access this line at a time
             */
            synchronized (MittausTietokanta.class) {
                /**
                 * Check again if the database has been created. If not, create it
                 * FallbackToDestructiveMigration prevents the loss of data if the database is migrated
                 */
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MittausTietokanta.class, "MittausTietokanta").fallbackToDestructiveMigration().build();
                }
            }
        }
        /**
         * Return the database if it has already been created
         */
        return INSTANCE;
    }
}

