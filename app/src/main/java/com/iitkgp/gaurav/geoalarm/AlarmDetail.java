package com.iitkgp.gaurav.geoalarm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AlarmDetail extends Activity {

    private String repeat="";
    private EditText edtTitle,edtTexts,edtRange;
    private String mlatitude,mlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm_detail);
        initialize();
        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        mlatitude=String.valueOf(bundle.getDouble("latitude"));
        mlongitude=String.valueOf(bundle.getDouble("longitude"));
    }

    private void initialize() {
        edtTitle = (EditText)findViewById(R.id.edtTitle);
        edtTexts = (EditText)findViewById(R.id.edtText);
        edtRange = (EditText)findViewById(R.id.edtRange);
    }

    public void onClick(View view){
        int id = view.getId();
        switch(id){
            case R.id.btnMon:
                Button btn = (Button)findViewById(id);
                btn.setAlpha(1);
                if(!repeat.contains("Mon")) repeat = repeat + "Mon";
                break;
            case R.id.btnTue:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                if(!repeat.contains("Tue")) repeat = repeat + "Tue";

                break;
            case R.id.btnWed:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                if(!repeat.contains("Wed")) repeat = repeat + "Wed";
                break;
            case R.id.btnThu:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                if(!repeat.contains("Thu")) repeat = repeat + "Thu";
                break;
            case R.id.btnFri:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                if(!repeat.contains("Fri")) repeat = repeat + "Fri";

                break;
            case R.id.btnSat:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                if(!repeat.contains("Sat")) repeat = repeat+"Sat";
                break;
            case R.id.btnSun:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                if(!repeat.contains("Sun")) repeat = repeat+"Sun";
                break;
            case R.id.btnOK:
                MySQLiteHelper mySQL = new MySQLiteHelper(this);
                MyAlarm mMyAlarm = new MyAlarm(edtTitle.getText().toString(),edtTexts.getText().toString(),mlatitude,
                                               mlongitude,edtRange.getText().toString(),repeat);                 //add data to database.....
                mySQL.addAlarm(mMyAlarm);
                Intent intent = new Intent(AlarmDetail.this,AlarmServices.class);
                stopService(intent);                          //used to update sql value to update alarm
                startService(intent);
                break;
        }
    }




}
