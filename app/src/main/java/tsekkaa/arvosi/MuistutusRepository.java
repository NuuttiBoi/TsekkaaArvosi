package tsekkaa.arvosi;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

/**
 * Stores data from the database and functions as an API for the ViewModel to interact with.
 * Inserting and deleting data asynchronously is handled by the repository by creating a subclass
 * which extends AsyncTask and overriding doInBackground().
 * Query results are wrapped in a LiveData object which is observed in the main thread.
 * This way data from the database can be inserted or deleted in the background in another thread
 * while the app is running and will be updated onto the screen in real time
 *
 * @author  Matleena Kankaanpää
 */

public class MuistutusRepository {

    //A data access object for querying the database
    private MuistutusDao muistutusDao;

    //Observable LiveData objects which contain lists queried from the database
    private LiveData<List<Muistutus>> muistutukset, muistutuksetKrono;

    /**
     * Class constructor
     * @param application Receives this application as a parameter, maintaining the context
     * throughout the whole application
     */
    public MuistutusRepository(Application application) {
        MittausTietokanta mittausTietokanta = MittausTietokanta.getInstance(application);
        muistutusDao = mittausTietokanta.muistutusDao();
        muistutukset = muistutusDao.haeMuistutukset();
        muistutuksetKrono = muistutusDao.haeMuistutuksetKrono();
    }

    /**
     * Retrieves a list of all saved reminders from the database in order of creation
     * @return A Live Data object containing a list of all reminders
     */
    public LiveData<List<Muistutus>> haeMuistutukset() {
        return muistutukset;
    }

    /**
     * Retrieves a list of all saved reminders from the database in chronological order
     * @return A Live Data object containing a list of all reminders in chronological order
     */
    public LiveData<List<Muistutus>> haeMuistutuksetKrono() {
        return muistutuksetKrono;
    }

    /**
     * Create a new object of a subclass of AsyncTask and to access its doInBackground method to
     * insert a new item into the database asynchronously
     * @param muistutus A new reminder to be added
     */
    public void lisaaMuistutus(Muistutus muistutus) {
        new InsertAsyncTask(muistutusDao).execute(muistutus);
    }

    /**
     * Create a new object of a subclass of AsyncTask and to access its doInBackground method to
     * delete the selected item from the database asynchronously
     * @param muistutus
     */
    public void poistaMuistutus(Muistutus muistutus) {
        new DeleteAsyncTask(muistutusDao).execute(muistutus);
    }

    /*
    Database transactions aren't carried out in the main thread as to not disrupt the UI.
    Create a subclass of AsyncTask which is needed override the doInBackground method
     */
    private static class InsertAsyncTask extends AsyncTask<Muistutus, Void, Void> {
        //A DAO is needed to interact with the database
        private MuistutusDao taskDao;

        /*
        Class constructor
        Initialize the DAO
         */
        private InsertAsyncTask(MuistutusDao muistutusDao) {
            this.taskDao = muistutusDao;
        }

        //Set the DAO to insert a new item into the database in a background thread
        @Override
        protected Void doInBackground(Muistutus... muistutukset) {
            taskDao.lisaaMuistutus(muistutukset[0]);
            return null;
        }
    }

    //Create a subclass of AsyncTask which is needed override the doInBackground method
    private static class DeleteAsyncTask extends AsyncTask<Muistutus, Void, Void> {
        private MuistutusDao taskDao;

        /*
        Class constructor
        Initialize the DAO because it is needed to interact with the database
         */
        private DeleteAsyncTask(MuistutusDao muistutusDao) {
            taskDao = muistutusDao;
        }

        //Set the DAO to delete the selected item from the database in a background thread
        @Override
        protected Void doInBackground(Muistutus... muistutukset) {
            taskDao.poistaMuistutus(muistutukset[0]);
            return null;
        }
    }
}