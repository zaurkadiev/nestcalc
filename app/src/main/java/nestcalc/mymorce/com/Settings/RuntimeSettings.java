package nestcalc.mymorce.com.Settings;

import android.content.Context;
import android.content.SharedPreferences;



public class RuntimeSettings {
    private SharedPreferences mSettingsPrefs;
    private String SETTINGS_SHARED_RUNTIME_PREFS_KEY = "settings_shared_runtime_prefs_key";
    private String LOG = getClass().getName();

    private Context context;

    private String SETTINGS_RATE_APP_INTERVAL = "settings_rate_app_interval";
    private int SETTINGS_RATE_APP_INTERVAL_DEFAULT = 2*24*60*60; // 2days


    public RuntimeSettings(Context context){
        this.context = context;
        mSettingsPrefs = context.getSharedPreferences(SETTINGS_SHARED_RUNTIME_PREFS_KEY, Context.MODE_PRIVATE);
    }

    public long getRateAppInterval(){
        return mSettingsPrefs.getLong(SETTINGS_RATE_APP_INTERVAL, SETTINGS_RATE_APP_INTERVAL_DEFAULT);
    }

    public void setRateAppInterval(long interval){
        mSettingsPrefs.edit()
                .putLong(SETTINGS_RATE_APP_INTERVAL, interval)
                .apply();

    }
}
