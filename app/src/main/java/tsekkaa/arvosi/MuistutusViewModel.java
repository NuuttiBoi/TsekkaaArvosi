package tsekkaa.arvosi;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import kotlin.annotation.MustBeDocumented;

/**
 * The ViewModel interacts with the repository and holds data for the UI to display.
 * Database transactions aren't carried out in the main thread as to not disrupt the UI.
 * The ViewModel creates an abstraction layer between the activity and the database.
 *
 * @author  Matleena Kankaanpää
 */

public class MuistutusViewModel extends AndroidViewModel {
    private MuistutusRepository muistutusRepository;
    private LiveData<List<Muistutus>> muistutukset;
    private LiveData<List<Muistutus>> muistutuksetKrono;

    /**
     * Class constructor
     * @param application Receives this application as a parameter, maintaining the context
     * throughout the whole application
     */
    public MuistutusViewModel(Application application) {
        super(application);
        muistutusRepository = new MuistutusRepository(application);
        muistutukset = muistutusRepository.haeMuistutukset();
        muistutuksetKrono = muistutusRepository.haeMuistutuksetKrono();
    }

    /**
     * Retrieves a list of all saved reminders from the repository in order of creation
     * @return A Live Data object containing a list of all reminders
     */
    public LiveData<List<Muistutus>> haeMuistutukset() {
        return muistutukset;
    }

    /**
     * Retrieves a list of all saved reminders from the repository in chronological order
     * @return A Live Data object containing a list of all reminders in chronological order
     */
    public LiveData<List<Muistutus>> haeMuistutuksetKrono() {
        return muistutuksetKrono;
    }

    /**
     * Method that adds a reminder to the repository
     * @param muistutus A new reminder object to be added
     */
    public void lisaaMuistutus(Muistutus muistutus) {
        muistutusRepository.lisaaMuistutus(muistutus);
    }

    /**
     * Method that deletes a reminder from the repository
     * @param muistutus The reminder object to be deleted
     */
    public void poistaMuistutus(Muistutus muistutus) {
        muistutusRepository.poistaMuistutus(muistutus);
    }
}