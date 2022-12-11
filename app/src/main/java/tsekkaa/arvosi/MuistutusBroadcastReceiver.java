package tsekkaa.arvosi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * The Broadcast Receiver defines how the alarm is displayed when it's received from the timed
 * pending intent
 *
 * @author Matleena Kankaanpää
 */

public class MuistutusBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String mitattavat = intent.getStringExtra(LisaaMuistutusActivity.EXTRA_MITATTAVAT);
        String lisatiedot = intent.getStringExtra(LisaaMuistutusActivity.EXTRA_LISATIEDOT);
        int id = intent.getIntExtra(LisaaMuistutusActivity.EXTRA_ID, 0);
        Intent notifIntent = new Intent(context, MainActivity.class);
        notifIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifIntent, 0);

        /**
         * Defines the content, priority and other settings of the notification alarm
         * The content title and text were selected by the user in LisaaMuistutusActivity and
         * they're passed to the Broadcast Receiver in the intent
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "tsekkaa_arvosi")
                .setSmallIcon(R.drawable.popup)
                .setContentTitle(mitattavat)
                .setContentText(lisatiedot)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(context);

        /**
         * A unique ID is needed for each alarm so they can be deleted later
         */
        notifManager.notify(id, builder.build());
    }
}


