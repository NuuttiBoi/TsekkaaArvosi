package tsekkaa.arvosi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Mittaus.class, Muistutus.class}, version = 6)
public abstract class MittausTietokanta extends RoomDatabase {

    public abstract MittausDao mittausDao();
    public abstract MuistutusDao muistutusDao();


    //Tietokannasta singleton-olio
    public static MittausTietokanta INSTANCE;

    public static MittausTietokanta getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MittausTietokanta.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MittausTietokanta.class, "MittausTietokanta").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}

