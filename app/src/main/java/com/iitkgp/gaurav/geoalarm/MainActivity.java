package com.iitkgp.gaurav.geoalarm;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {             //ActionBarActivity used to implement Theme.AppCombat

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String data[] = {"Gaurav","Kumar","19"};
    String dist[]={"500","600","100"};
    private ArrayList<ListViewItem> dataList;
    MySQLiteHelper mySQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        dataList = new ArrayList<ListViewItem>();
        populate(dataList);
        mySQL = new MySQLiteHelper(this);
        MyAlarm mMyAlarm = new MyAlarm("Office","Dont wake me up","500","MoTu");
        mySQL.addAlarm(mMyAlarm);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
       // mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager


        // specify an adapter (see also next example)
        mAdapter = new UIAdapter(this,dataList);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void populate(ArrayList<ListViewItem> dataList) {
        for(int i =0 ;i<2;i++){
            ListViewItem mItem = new ListViewItem(data[i],dist[i],R.drawable.ic_geoalarm_btn_on);
            dataList.add(mItem);
        }
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
        else if (id==R.id.action_new_alarm){
            Intent intent = new Intent(MainActivity.this,AlarmDetail.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
