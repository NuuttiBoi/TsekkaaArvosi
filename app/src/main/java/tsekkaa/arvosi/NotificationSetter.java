package tsekkaa.arvosi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

/**
 * A tool to create a notification channel and define the content and priority of sent notifications
 *
 * @author  Matleena Kankaanpää
 */

public class NotificationSetter extends ContextWrapper {
    private NotificationManager mManager;

    /**
     * Notification channel ID and name
     */
    public static final String kanavaID = "TsekkaaArvosi";
    public static final String kanavaNimi = "MuistutusKanava";

    /**
     * Class constructor
     * @param base Activity context
     */
    public NotificationSetter(Context base) {
        super(base);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    /**
     * Creates a notification channel and sets the priotity and importance settings
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannel() {
        //Define the channel
        NotificationChannel muistutusKanava = new NotificationChannel(kanavaID, kanavaNimi, NotificationManager.IMPORTANCE_DEFAULT);
        muistutusKanava.enableLights(true);
        muistutusKanava.enableVibration(true);
        muistutusKanava.setLightColor(R.color.ilmoitus_tehoste);
        muistutusKanava.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        //Create the channel using the manager
        getManager().createNotificationChannel(muistutusKanava);
    }

    /**
     * Returns a singleton for managing notifications, or creates one if it hasn't already been created
     * @return A Notification Manager singleton
     */
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    /**
     * Creates a Notification Builder which defines the title, text and icon for all notifications on the channel
     * @param mitattavat A string listing measurements the user selected to be reminded of
     * @param lisatiedot A custom note set by the user
     * @return
     */
    public NotificationCompat.Builder luoMuistutus(String mitattavat, String lisatiedot) {
        return new NotificationCompat.Builder(getApplicationContext(), kanavaID)
                .setContentTitle(mitattavat)
                .setContentText(lisatiedot)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.popup);
    }
}
