package tsekkaa.arvosi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MuistutusBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notifIntent = new Intent(context, MainActivity.class);
        notifIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "tsekkaaarvosi")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("muistutus")
                .setContentText("wip")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notifManager = NotificationManagerCompat.from(context);

        notifManager.notify(1000, builder.build());
    }
}


