package com.iitkgp.gaurav.geoalarm;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by gaurav on 6/30/2015.
 */

public class MySQLite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Alarms";

    public MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENT_TABLE = "CREATE TABLE alarmlist ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "title TEXT, " + "summary TEXT," + "range TEXT," + "repeat TEXT," + "latitude TEXT," + "longitude TEXT )";
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarmlist");
        this.onCreate(db);
    }

    private static final String TABLE_ALARM = "alarmlist";
    private static final String ID = "_id";
    private static final String TITLE = "title";
    private static final String SUMMARY = "summary";
    private static final String RANGE = "range";
    private static final String REPEAT = "repeat";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";


    private static final String[] COLUMNS = {ID, TITLE, SUMMARY, RANGE, REPEAT, LATITUDE, LONGITUDE};

    public void addAlarm(MyAlarm mMyAlarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, mMyAlarm.getTitle());
        values.put(SUMMARY, mMyAlarm.getTexts());
        values.put(RANGE, mMyAlarm.getRange());
        values.put(REPEAT, mMyAlarm.getRepeat());
        values.put(LATITUDE, mMyAlarm.getLatitude());
        values.put(LONGITUDE, mMyAlarm.getLongitude());
        db.insertOrThrow(TABLE_ALARM, null, values);
        db.close();
    }

    public boolean deleteTitle(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ALARM, TITLE + "=" + title, null) > 0;
    }

    public boolean deleteId(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ALARM, ID + "=" + id, null) > 0;
    }

    public String getRange(String title)                                  // overriding this function
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_ALARM, null, "TITLE=?", new String[]{title}, null, null, null);
        if(cursor.getCount()<1) // Invalid Title
        {
            cursor.close();
            return "0";
        }
        cursor.moveToFirst();
        String range= cursor.getString(cursor.getColumnIndex(RANGE));
        cursor.close();
        return range;
    }

    public List<MyAlarm> getAllDetails()
    {
        List<MyAlarm> listAlarm = new LinkedList<MyAlarm>();
        String query = "SELECT * FROM " + TABLE_ALARM;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        MyAlarm mMyAlarm = null;
        if (cursor.moveToFirst())
        {
            do
            {
                mMyAlarm = new MyAlarm();
                //mMyAlarm.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
                mMyAlarm.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                mMyAlarm.setTexts(cursor.getString(cursor.getColumnIndex(SUMMARY)));
                mMyAlarm.setRange(cursor.getString(cursor.getColumnIndex(RANGE)));
                mMyAlarm.setRepeat(cursor.getString(cursor.getColumnIndex(REPEAT)));
                mMyAlarm.setLatitude(cursor.getString(cursor.getColumnIndex(LATITUDE)));
                mMyAlarm.setLongitude(cursor.getString(cursor.getColumnIndex(LONGITUDE)));
                listAlarm.add(mMyAlarm);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listAlarm;
    }

    public int getCount(){
        String query = "SELECT * FROM " + TABLE_ALARM;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count=0;
        if (cursor.moveToFirst())
        {
            do
            {
                count++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        return count;
    }

    public MyAlarm getItemAtId(int id){
        String query = "SELECT * FROM " + TABLE_ALARM;
        SQLiteDatabase db = this.getWritableDatabase();
        MyAlarm alarm = new MyAlarm();
        Cursor cursor = db.rawQuery(query, null);
        int count=0;
        if (cursor.moveToFirst())
        {
            do {
                if (id == count) {
                    alarm.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                    alarm.setTexts(cursor.getString(cursor.getColumnIndex(SUMMARY)));
                    alarm.setRange(cursor.getString(cursor.getColumnIndex(RANGE)));
                    alarm.setRepeat(cursor.getString(cursor.getColumnIndex(REPEAT)));
                    alarm.setLatitude(cursor.getString(cursor.getColumnIndex(LATITUDE)));
                    alarm.setLongitude(cursor.getString(cursor.getColumnIndex(LONGITUDE)));
                }
                count++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        return alarm;
    }

}
