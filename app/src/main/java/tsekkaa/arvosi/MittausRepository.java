package tsekkaa.arvosi;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class MittausRepository {

    MittausDao mittausDao;

    //Säilyttää kontekstin koko sovelluksessa
    MittausRepository(Application application) {
        MittausTietokanta mittausTietokanta = MittausTietokanta.getInstance(application);
        mittausDao = mittausTietokanta.mittausDao();
    }

    LiveData<List<Mittaus>> haeMittaukset() {
        return mittausDao.haeMittaukset();
    }

    void insert(Mittaus mittaus) {
        new InsertAsyncTask(mittausDao).execute(mittaus);
    }

    private static class InsertAsyncTask extends AsyncTask<Mittaus, Void, Void> {
        private MittausDao taskDao;

        InsertAsyncTask(MittausDao mittausDao) {
            taskDao = mittausDao;
        }

        @Override
        protected Void doInBackground(Mittaus... mittaukset) {
            taskDao.lisaaMittaus(mittaukset[0]);
            return null;
        }
    }
}