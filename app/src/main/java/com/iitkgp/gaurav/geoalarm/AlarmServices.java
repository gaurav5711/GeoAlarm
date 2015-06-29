package com.iitkgp.gaurav.geoalarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gaurav on 6/28/2015.
 */
public class AlarmServices extends Service implements LocationListener {

    private MySQLiteHelper mySQL;
    private List<MyAlarm> mListAlarm;
    private AlarmActivity mAlarmActivity;
    private LocationManager mLocationManager;
    private int[] ranges;
    private int counter = 0;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mListAlarm = new LinkedList<MyAlarm>();
        mySQL = new MySQLiteHelper(this);
        mListAlarm = mySQL.getAllDetails();
        //      alarmThread.start();
        ranges = new int[mySQL.getCount()];
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, this);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0, 0, this);
        populate();
    }

    private void populate() {
        for(MyAlarm mMyAlarm :mListAlarm){
            ranges[counter] = Integer.parseInt(mMyAlarm.getRange());
            counter++;
        }
    }

    //    Thread alarmThread = new Thread(){
//        @Override
//        public void run() {
//         mAlarmActivity = new AlarmActivity(mListAlarm);
//        }
//    };

    @Override
    public void onLocationChanged(Location location) {
        MyAlarm mAlarm = new MyAlarm();
        for(int i=0;i<mySQL.getCount();i++) {
            mAlarm = mySQL.getItemAtId(i);
            Location selected_location = new Location("Alarm");
            selected_location.setLatitude(Double.parseDouble(mAlarm.getLatitude()));
            selected_location.setLongitude(Double.parseDouble(mAlarm.getLongitude()));
            Location near_locations = new Location("Present");
            near_locations.setLatitude(location.getLatitude());
            near_locations.setLongitude(location.getLongitude());
            double distance = selected_location.distanceTo(near_locations);
            if(distance<=Double.parseDouble(mAlarm.getRange())){
                //Vibrate
                //showNotification
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0, this);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0, 0, this);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(),"GPS must be enabled",Toast.LENGTH_LONG).show();
    }
}
