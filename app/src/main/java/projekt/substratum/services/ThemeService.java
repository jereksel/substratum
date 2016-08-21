package projekt.substratum.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import projekt.substratum.util.AntiPiracyCheck;

/**
 * @author Nicholas Chum (nicholaschum)
 */

public class ThemeService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        AlarmManager alarm_manager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        Intent alarm_intent = new Intent(this, ThemeService.class);
        PendingIntent alarm_pendingintent = PendingIntent.getBroadcast(this, 0, alarm_intent, 0);

        alarm_manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR,
                alarm_pendingintent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new AntiPiracyCheck().AntiPiracyCheck(this);
        return START_STICKY;
    }
}