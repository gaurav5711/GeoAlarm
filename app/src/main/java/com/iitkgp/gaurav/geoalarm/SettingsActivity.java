package com.iitkgp.gaurav.geoalarm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class SettingsActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private static final String KEY_SATELLITE_VIEW="pref_key_satellite_view";
    private static final String KEY_TRAFFIC_VIEW="pref_key_traffic_view";
    private static final String KEY_ALARMRANGE_VIEW="pref_key_show_alarm_range";
    private static final String KEY_NOTIFICATION_VIEW="pref_key_notification_bar";
    private static final String KEY_VIBRATOR_VIEW="pref_key_alarm_vibration";

    SharedPreferences sharedPref;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        listener =
                new SharedPreferences.OnSharedPreferenceChangeListener() {
                    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                        Log.d(TAG,"Setting Changed");
                        if(key.equals(KEY_SATELLITE_VIEW)){
                        MainActivity.SATELLITEVIEW=(!MainActivity.SATELLITEVIEW);
                        }
                        else if(key.equals(KEY_TRAFFIC_VIEW)){
                            MainActivity.TRAFFICVIEW=(!MainActivity.TRAFFICVIEW);
                        }
                        else if(key.equals(KEY_ALARMRANGE_VIEW)){
                            MainActivity.SHOWALARMRANGE=(!MainActivity.SHOWALARMRANGE);
                        }
                        else if(key.equals(KEY_NOTIFICATION_VIEW)){
                            MainActivity.NOTIFICATIONBAR=(!MainActivity.NOTIFICATIONBAR);
                        }
                        else if(key.equals(KEY_VIBRATOR_VIEW)){
                            MainActivity.ALARMVIBRATOR=(!MainActivity.ALARMVIBRATOR);
                        }
                    }
                };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPref.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
