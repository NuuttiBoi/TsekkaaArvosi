package tsekkaa.arvosi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;


/**
 * The Alert Receiver defines how the alarm is displayed when it's received from the timed pending intent
 *
 * @author Matleena Kankaanpää
 */

public class AlertReceiver extends BroadcastReceiver {
    public static final int ID = 1;
    private NotificationSetter notificationSetter;

    /**
     * Gets called once the notification goes off
     * @param intent Includes the text that will appear on the user's screen in a popup.
     * This will change depending which toggle boxes were checked or if the user wrote a custom
     * message in the text edit field
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String mitattavat = intent.getStringExtra(LisaaMuistutusActivity.EXTRA_MITATTAVAT);
        String lisatiedot = intent.getStringExtra(LisaaMuistutusActivity.EXTRA_LISATIEDOT);

        notificationSetter = new NotificationSetter(context);
        NotificationCompat.Builder notifBuilder = notificationSetter.luoMuistutus(mitattavat, lisatiedot);
        notificationSetter.getManager().notify(ID, notifBuilder.build());
    }
}
