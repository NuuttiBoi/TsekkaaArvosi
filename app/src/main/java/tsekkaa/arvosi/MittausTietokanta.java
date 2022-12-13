package tsekkaa.arvosi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * A Room database which stores measurement and reminder objects. Caches data on the device locally
 *
 * @author  Matleena Kankaanpää
 * @version 1.0
 * @since   2022-12-14
 * @see     <a href="https://www.youtube.com/watch?v=ARpn-1FPNE4&list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118>Tutorial by Coding in Flow on YouTube</a>
 */

/*
A database is created with the @Database annotation. Contains Mittaus and Muistutus tables.
The version number is incremented every time the structure of the database is modified.
 */
@Database(entities = {Mittaus.class, Muistutus.class}, version = 7)
public abstract class MittausTietokanta extends RoomDatabase {

    /**
     * Different data access objects for both kinds of records for code separation
     */
    public abstract MittausDao mittausDao();
    public abstract MuistutusDao muistutusDao();

    /**
     * Initialize the database object
     */
    public static MittausTietokanta INSTANCE;

     /** Returns the single instance of the database or creates it if it hasn't been created yet
     * @param context The state of the application
     * @return The database singleton
     */
    public static MittausTietokanta getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MittausTietokanta.class) {
                /**
                 * Only one thread can access this line at a time
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

