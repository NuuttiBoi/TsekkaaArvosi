package tsekkaa.arvosi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Database transactions aren't carried out in the main thread to not disrupt the UI
 * The ViewModel creates an abstraction layer between the activity and the database
 * @author Matleena Kankaanpää
 */

public class MittausViewModel extends AndroidViewModel {
    MittausRepository mittausRepository;

    LiveData<List<Mittaus>> mittaukset;
    LiveData<List<Mittaus>> ylapaineMittaukset;
    LiveData<List<Mittaus>> alapaineMittaukset;
    LiveData<List<Mittaus>> sykeMittaukset;
    LiveData<List<Mittaus>> ypApMittaukset;
    LiveData<List<Mittaus>> ypApSykeMittaukset;
    LiveData<List<Mittaus>> verensokeriMittaukset;
    LiveData<List<Mittaus>> happipitoisuusMittaukset;

    public MittausViewModel(Application application) {
        super(application);
        mittausRepository = new MittausRepository(application);
        mittaukset = mittausRepository.haeMittaukset();
        ylapaineMittaukset = mittausRepository.haeYlapaineMittaukset();
        alapaineMittaukset = mittausRepository.haeAlapaineMittaukset();
        sykeMittaukset = mittausRepository.haeSykeMittaukset();
        ypApMittaukset = mittausRepository.haeYpApMittaukset();
        ypApSykeMittaukset = mittausRepository.haeYpApSykeMittaukset();
        verensokeriMittaukset = mittausRepository.haeVerensokeriMittaukset();
        happipitoisuusMittaukset = mittausRepository.haeHappipitoisuusMittaukset();
    }

    LiveData<List<Mittaus>> haeMittaukset() {
        return mittaukset;
    }

    LiveData<List<Mittaus>> haeYlapaineMittaukset() {
        return ylapaineMittaukset;
    }

    LiveData<List<Mittaus>> haeAlapaineMittaukset() {
        return alapaineMittaukset;
    }

    LiveData<List<Mittaus>> haeSykeMittaukset() {
        return sykeMittaukset;
    }

    LiveData<List<Mittaus>> haeYpApMittaukset() {
        return ypApMittaukset;
    }

    LiveData<List<Mittaus>> haeYpApSykeMittaukset() {
        return ypApSykeMittaukset;
    }

    LiveData<List<Mittaus>> haeVerensokeriMittaukset() {
        return verensokeriMittaukset;
    }

    LiveData<List<Mittaus>> haeHappipitoisuusMittaukset() {
        return happipitoisuusMittaukset;
    }

    public void lisaaMittaus(Mittaus mittaus) {
        mittausRepository.insert(mittaus);
    }

    //public void lisaaypApMittaus(YlapaineMittaus ylapaineMittaus){mittausRepository.insert(ylapaineMittaus);}

    public void poistaMittaus(Mittaus mittaus) {
        mittausRepository.poistaMittaus(mittaus);
    }
}