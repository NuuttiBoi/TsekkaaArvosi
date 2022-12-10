package tsekkaa.arvosi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import kotlin.annotation.MustBeDocumented;

public class MuistutusViewModel extends AndroidViewModel {
    private MuistutusRepository muistutusRepository;
    private LiveData<List<Muistutus>> muistutukset;

    public MuistutusViewModel(Application application) {
        super(application);
        muistutusRepository = new MuistutusRepository(application);
        muistutukset = muistutusRepository.haeMuistutukset();
    }

    public LiveData<List<Muistutus>> haeMuistutukset() {
        return muistutukset;
    }

    public void lisaaMuistutus(Muistutus muistutus) {
        muistutusRepository.lisaaMuistutus(muistutus);
    }

    public void poistaMuistutus(Muistutus muistutus) {
        muistutusRepository.poistaMuistutus(muistutus);
    }
}