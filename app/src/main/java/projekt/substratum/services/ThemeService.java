package projekt.substratum.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;

import projekt.substratum.util.AntiPiracyCheck;

/**
 * @author Nicholas Chum (nicholaschum)
 */

public class ThemeService extends Service {

    private static Runnable runnable = null;
    private Context context = this;
    private Handler handler = null;
    private SharedPreferences prefs;

    private int CONFIG_TIME_PIRACY_CHECKER = 60000; // 1 sec == 1000ms

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (prefs.getBoolean("packages_changed", true)) {
                    new AntiPiracyCheck().AntiPiracyCheck(context);
                }
                handler.postDelayed(runnable, CONFIG_TIME_PIRACY_CHECKER);
            }
        };
        handler.postDelayed(runnable, CONFIG_TIME_PIRACY_CHECKER);
    }
}