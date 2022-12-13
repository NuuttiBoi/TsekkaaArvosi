package tsekkaa.arvosi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The ViewModel interacts with the repository and holds data for the UI to display.
 * Database transactions aren't carried out in the main thread as to not disrupt the UI.
 * The ViewModel creates an abstraction layer between the activity and the database.
 *
 * @author  Matleena Kankaanpää
 */

public class MittausViewModel extends AndroidViewModel {
    private MittausRepository mittausRepository;
    private LiveData<List<Mittaus>> mittaukset;
    private LiveData<List<Mittaus>> ylapaineMittaukset;
    private LiveData<List<Mittaus>> alapaineMittaukset;
    private LiveData<List<Mittaus>> sykeMittaukset;
    private LiveData<List<Mittaus>> ypApMittaukset;
    private LiveData<List<Mittaus>> ypApSykeMittaukset;
    private LiveData<List<Mittaus>> verensokeriMittaukset;
    private LiveData<List<Mittaus>> happipitoisuusMittaukset;

    /**
     * Class constructor
     * @param application Receives this application as a parameter, maintaining the context
     * throughout the whole application
     */
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

    /**
     * Retrieves a list of measurements from the repository relevant to tracking blood pressure and heart rate
     * @return A Live Data object containing a list of all measurements, may contain null values
     */
    public LiveData<List<Mittaus>> haeMittaukset() {
        return mittaukset;
    }

    /**
     * Retrieves a list of measurements from the repository relevant to tracking blood pressure and heart rate
     * @return A Live Data object containing a list of measurements where blood pressure
     * and heart rate are included
     */
    public LiveData<List<Mittaus>> haeYpApSykeMittaukset() {
        return ypApSykeMittaukset;
    }

    /**
     * Retrieves a list of measurements from the repository relevant to tracking blood sugar
     * @return A Live Data object containing a list of measurements where blood sugar is included
     */
    public LiveData<List<Mittaus>> haeVerensokeriMittaukset() {
        return verensokeriMittaukset;
    }

    /**
     * Retrieves a list of measurements from the repository relevant to tracking blood oxygen
     * @return A Live Data object containing a list of measurements where blood sugar is included
     */
    public LiveData<List<Mittaus>> haeHappipitoisuusMittaukset() {
        return happipitoisuusMittaukset;
    }

    /**
     * Adds a new measurement to the repository
     * @param mittaus A new measurement object to be added
     */
    public void lisaaMittaus(Mittaus mittaus) {
        mittausRepository.insert(mittaus);
    }
}