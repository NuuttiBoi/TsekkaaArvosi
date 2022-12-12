package tsekkaa.arvosi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationSetter extends ContextWrapper {
    public static final String kanavaID = "TsekkaaArvosi";
    public static final String kanavaNimi = "MuistutusKanava";
    private NotificationManager mManager;

    public NotificationSetter(Context base) {
        super(base);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannel() {
        NotificationChannel muistutusKanava = new NotificationChannel(kanavaID, kanavaNimi, NotificationManager.IMPORTANCE_DEFAULT);
        muistutusKanava.enableLights(true);
        muistutusKanava.enableVibration(true);
        muistutusKanava.setLightColor(R.color.ilmoitus_tehoste);
        muistutusKanava.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(muistutusKanava);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder luoMuistutus(String mitattavat, String lisatiedot) {
        return new NotificationCompat.Builder(getApplicationContext(), kanavaID)
                .setContentTitle(mitattavat)
                .setContentText(lisatiedot)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.popup);
    }
}
