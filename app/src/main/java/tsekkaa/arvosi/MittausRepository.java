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

    LiveData<List<Mittaus>> haeYlapaineMittaukset() {
        return mittausDao.haeYlapaineMittaukset();
    }

    LiveData<List<Mittaus>> haeAlapaineMittaukset() {
        return mittausDao.haeAlapaineMittaukset();
    }

    LiveData<List<Mittaus>> haeSykeMittaukset() {
        return mittausDao.haeSykeMittaukset();
    }

    LiveData<List<Mittaus>> haeYpApMittaukset() {
        return mittausDao.haeYpApMittaukset();
    }

    LiveData<List<Mittaus>> haeYpApSykeMittaukset() {return mittausDao.haeYpApSykeMittaukset();}

    LiveData<List<Mittaus>> haeVerensokeriMittaukset() {
        return mittausDao.haeVerensokeriMittaukset();
    }

    LiveData<List<Mittaus>> haeHappipitoisuusMittaukset() {
        return mittausDao.haeHappipitoisuusMittaukset();
    }

    public void insert(Mittaus mittaus) {
        new InsertAsyncTask(mittausDao).execute(mittaus);
    }

    public void poistaMittaus(Mittaus mittaus) {
        new DeleteAsyncTask(mittausDao).execute(mittaus);
    }

    private static class InsertAsyncTask extends AsyncTask<Mittaus, Void, Void> {
        private MittausDao taskDao;

        private InsertAsyncTask(MittausDao mittausDao) {
            taskDao = mittausDao;
        }

        @Override
        protected Void doInBackground(Mittaus... mittaukset) {
            taskDao.lisaaMittaus(mittaukset[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Mittaus, Void, Void> {
        private MittausDao taskDao;

        private DeleteAsyncTask(MittausDao mittausDao) {
            taskDao = mittausDao;
        }

        @Override
        protected Void doInBackground(Mittaus... mittaukset) {
            taskDao.poistaMittaus(mittaukset[0]);
            return null;
        }
    }
}