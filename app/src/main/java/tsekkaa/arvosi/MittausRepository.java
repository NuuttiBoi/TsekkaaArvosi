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

public class MittausRepository {

    //A data access object for querying the database
    private MittausDao mittausDao;

    /**
     * Class constructor
     * @param application Receives this application as a parameter, maintaining the context
     * throughout the whole application
     */
    public MittausRepository(Application application) {
        MittausTietokanta mittausTietokanta = MittausTietokanta.getInstance(application);
        mittausDao = mittausTietokanta.mittausDao();
    }


    /**
     * Retrieves a list of all measurements from the database
     * @return A Live Data object containing a list of all measurements
     */
    LiveData<List<Mittaus>> haeMittaukset() {
        return mittausDao.haeMittaukset();
    }

    /**
     * Retrieves a list of measurements from the database relevant to tracking systolic blood pressure
     * @return A Live Data object containing a list of measurements where systolic blood pressure is included
     */
    LiveData<List<Mittaus>> haeYlapaineMittaukset() {
        return mittausDao.haeYlapaineMittaukset();
    }

    /**
     * Retrieves a list of measurements from the database relevant to tracking diastolic blood pressure
     * @return A Live Data object containing a list of measurements where diastolic blood pressure is included
     */
    LiveData<List<Mittaus>> haeAlapaineMittaukset() {
        return mittausDao.haeAlapaineMittaukset();
    }

    /**
     * Retrieves a list of measurements from the database relevant to tracking heart rate
     * @return A Live Data object containing a list of measurements where systolic blood pressure is included
     */
    LiveData<List<Mittaus>> haeSykeMittaukset() {
        return mittausDao.haeSykeMittaukset();
    }


    /**
     * Retrieves a list of measurements from the database relevant to tracking blood pressure
     * @return A Live Data object containing a list of measurements where systolic and diastolic blood pressure are included
     */
    LiveData<List<Mittaus>> haeYpApMittaukset() {
        return mittausDao.haeYpApMittaukset();
    }


    /**
     * Retrieves a list of measurements from the database relevant to tracking blood pressure and heart rate
     * @return A Live Data object containing a list of measurements where systolic blood pressure,
     * diastolic blood pressure and heart rate are included
     */
    LiveData<List<Mittaus>> haeYpApSykeMittaukset() {return mittausDao.haeYpApSykeMittaukset();}


    /**
     * Retrieves a list of measurements from the database relevant to tracking blood sugar
     * @return A Live Data object containing a list of measurements where systolic blood sugar is included
     */
    LiveData<List<Mittaus>> haeVerensokeriMittaukset() {
        return mittausDao.haeVerensokeriMittaukset();
    }


    /**
     * Retrieves a list of measurements from the database relevant to tracking blood oxygen
     * @return A Live Data object containing a list of measurements where systolic blood oxygen is included
     */
    LiveData<List<Mittaus>> haeHappipitoisuusMittaukset() {
        return mittausDao.haeHappipitoisuusMittaukset();
    }


    /**
     * Create a new object of a subclass of AsyncTask and to access its doInBackground method to
     * insert a new item into the database asynchronously
     * @param mittaus A new reminder to be added
     */
    public void insert(Mittaus mittaus) {
        new InsertAsyncTask(mittausDao).execute(mittaus);
    }

    /*
    Database transactions aren't carried out in the main thread as to not disrupt the UI.
    Create a subclass of AsyncTask which is needed override the doInBackground method
     */
    private static class InsertAsyncTask extends AsyncTask<Mittaus, Void, Void> {
        //A DAO is needed to interact with the database
        private MittausDao taskDao;

        /*
        Class constructor
        Initialize the DAO
         */
        private InsertAsyncTask(MittausDao mittausDao) {
            taskDao = mittausDao;
        }
        //Set the DAO to insert a new item into the database in a background thread
        @Override
        protected Void doInBackground(Mittaus... mittaukset) {
            taskDao.lisaaMittaus(mittaukset[0]);
            return null;
        }
    }
}