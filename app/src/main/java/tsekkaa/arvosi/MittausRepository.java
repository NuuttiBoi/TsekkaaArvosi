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

    void insert(Mittaus mittaus) {
        new InsertAsyncTask(mittausDao).execute(mittaus);
    }

    void delete() { mittausDao.delete();}

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