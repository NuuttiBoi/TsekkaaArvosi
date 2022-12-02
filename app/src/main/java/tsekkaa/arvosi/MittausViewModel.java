package tsekkaa.arvosi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MittausViewModel extends AndroidViewModel {
    MittausRepository mittausRepository;

    LiveData<List<Mittaus>> mittaukset;

    public MittausViewModel(Application application) {
        super(application);
        mittausRepository = new MittausRepository(application);
        mittaukset = mittausRepository.haeMittaukset();
    }

    LiveData<List<Mittaus>> haeMittaukset() {
        return mittaukset;
    }

    public void lisaaMittaus(Mittaus mittaus) {
        mittausRepository.insert(mittaus);
    }
}