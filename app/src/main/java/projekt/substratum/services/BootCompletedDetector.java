package projekt.substratum.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author Nicholas Chum (nicholaschum)
 */

public class BootCompletedDetector extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editor.remove("packages_changed");
            editor.apply();
            Intent pushIntent = new Intent(context, ThemeService.class);
            context.startService(pushIntent);
        }
    }
}