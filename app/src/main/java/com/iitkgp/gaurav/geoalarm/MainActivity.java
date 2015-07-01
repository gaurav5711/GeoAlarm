package com.iitkgp.gaurav.geoalarm;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AlarmListener {             //AppCompatActivity used to implement Theme.AppCombat

    private String TAG = this.getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
//    AlarmDetail mAlarmDetail;
//    String data[] = {"Gaurav","Kumar","19"};
//    String dist[]={"500","600","100"};
    public AlarmDetail mAlarmDetail;
    public AlarmListener mAlarmListener;
    public static ArrayList<ListViewItem> dataList;
    MySQLite mMySQLite;
//    public DeleteListener mDeleteListener;

    public static boolean SATELLITEVIEW=true;
    public static boolean TRAFFICVIEW=false;
    public static boolean SHOWALARMRANGE=true;
    public static boolean NOTIFICATIONBAR=true;
    public static boolean ALARMVIBRATOR=true;
//    MySQLiteHelper mySQL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = new ArrayList<ListViewItem>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle==null){
            Log.e(TAG,"Intent is null");
        }
//        else {
//            ListViewItem item = new ListViewItem(bundle.getString("maptitle"),bundle.getString("maprange"),bundle.getInt("mapid"));
//            dataList.add(item);
//        }
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        populateList();
        inflateView();
    }

    private void populateList() {
        mMySQLite = new MySQLite(this);
        List<MyAlarm> alarmList= new LinkedList<MyAlarm>();
        alarmList = mMySQLite.getAllDetails();
        int n = mMySQLite.getCount();
        for(int i=(n-1);i>=0;i--){
            MyAlarm alarm = alarmList.get(i);
            dataList.add(new ListViewItem(alarm.getTitle(),alarm.getRange(),R.drawable.ic_alarm_btn_off));
        }
    }

//    public AlarmListener mAlarmListener = new AlarmListener() {
//        @Override
//        public void onAlarmCreated(ListViewItem mListViewItem) {
//        dataList.add(mListViewItem);
//            inflateView();
//        }
//    };

    public DeleteListener mDeviceListener = new DeleteListener() {
        @Override
        public void onDeleted(int pos,String title) {
            Toast.makeText(MainActivity.this,"deleted",Toast.LENGTH_LONG).show();
        }
    };

    private void inflateView(){
        if(dataList!=null) {
            mAdapter = new UIAdapter(this, dataList,mDeviceListener);
            mRecyclerView.setAdapter(mAdapter);
        }
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);       //Settings Activity
            startActivity(intent);
            return true;
        }
        else if (id==R.id.new_alarm){
            Intent intent = new Intent(MainActivity.this,MapActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAlarmCreated(ListViewItem mListViewItem) {
     Log.e(TAG,mListViewItem.distance);
    }
}
