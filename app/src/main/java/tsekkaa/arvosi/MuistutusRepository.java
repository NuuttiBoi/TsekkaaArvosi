package tsekkaa.arvosi;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

/**
 *
 * @author
 */
public class MuistutusRepository {

    private MuistutusDao muistutusDao;
    private LiveData<List<Muistutus>> muistutukset, muistutuksetKrono;

    public MuistutusRepository(Application application) {
        MittausTietokanta mittausTietokanta = MittausTietokanta.getInstance(application);
        muistutusDao = mittausTietokanta.muistutusDao();
        muistutukset = muistutusDao.haeMuistutukset();
        muistutuksetKrono = muistutusDao.haeMuistutuksetKrono();
    }

    public LiveData<List<Muistutus>> haeMuistutukset() {
        return muistutukset;
    }

    public LiveData<List<Muistutus>> haeMuistutuksetKrono() {
        return muistutuksetKrono;
    }

    public void lisaaMuistutus(Muistutus muistutus) {
        new InsertAsyncTask(muistutusDao).execute(muistutus);
    }

    public void poistaMuistutus(Muistutus muistutus) {
        new DeleteAsyncTask(muistutusDao).execute(muistutus);
    }

    private static class InsertAsyncTask extends AsyncTask<Muistutus, Void, Void> {
        private MuistutusDao taskDao;

        private InsertAsyncTask(MuistutusDao muistutusDao) {
            this.taskDao = muistutusDao;
        }

        @Override
        protected Void doInBackground(Muistutus... muistutukset) {
            taskDao.lisaaMuistutus(muistutukset[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Muistutus, Void, Void> {
        private MuistutusDao taskDao;

        private DeleteAsyncTask(MuistutusDao muistutusDao) {
            taskDao = muistutusDao;
        }

        @Override
        protected Void doInBackground(Muistutus... muistutukset) {
            taskDao.poistaMuistutus(muistutukset[0]);
            return null;
        }
    }
}