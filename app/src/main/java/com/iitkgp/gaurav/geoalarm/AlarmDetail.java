package com.iitkgp.gaurav.geoalarm;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);
        initialize();
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
                repeat = repeat+"Mon";
                break;
            case R.id.btnTue:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                repeat = repeat+"Tue";
                break;
            case R.id.btnWed:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                repeat = repeat+"Wed";
                break;
            case R.id.btnThu:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                repeat = repeat+"Thu";
                break;
            case R.id.btnFri:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                repeat = repeat+"Fri";
                break;
            case R.id.btnSat:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                repeat = repeat+"Sat";
                break;
            case R.id.btnSun:
                btn = (Button)findViewById(id);
                btn.setAlpha(1);
                repeat = repeat+"Sun";
                break;
            case R.id.btnOK:
                MySQLiteHelper mySQL = new MySQLiteHelper(this);
                MyAlarm mMyAlarm = new MyAlarm(edtTitle.getText().toString(),edtTexts.getText().toString(),
                                               edtRange.getText().toString(),repeat);                 //add data to database.....
                break;
        }
    }




}
