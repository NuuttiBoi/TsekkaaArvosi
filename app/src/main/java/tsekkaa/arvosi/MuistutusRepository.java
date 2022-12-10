package tsekkaa.arvosi;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class MuistutusRepository {

    MuistutusDao muistutusDao;

    MuistutusRepository(Application application) {
        MittausTietokanta mittausTietokanta = MittausTietokanta.getInstance(application);
        muistutusDao = mittausTietokanta.muistutusDao();
    }

    LiveData<List<Muistutus>> haeMuistutukset() {
        return muistutusDao.haeMuistutukset();
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
            taskDao = muistutusDao;
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