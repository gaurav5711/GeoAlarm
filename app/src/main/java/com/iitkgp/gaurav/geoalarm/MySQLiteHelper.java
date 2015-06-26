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
 * Created by gaurav on 6/26/2015.
 */

public class MySQLiteHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AlarmList";
    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_ALARM_TABLE = "CREATE TABLE alarm ( "+"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"title TEXT, "+"texts TEXT,"+"range TEXT,"+"repeat TEXT )";
        db.execSQL(CREATE_ALARM_TABLE);                                        //repeat--stores first two letter of selected days else 0
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS alarm");
        this.onCreate(db);
    }
    private static final String TABLE_ALARM = "alarm";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String TEXTS = "texts";
    private static final String RANGE = "range";
    private static final String REPEAT = "repeat";

    private static final String[] COLUMNS = {ID,TITLE,TEXTS,RANGE,REPEAT};
    public void addAlarm(MyAlarm mMyAlarm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, mMyAlarm.getTitle());
        values.put(TEXTS, mMyAlarm.getTexts());
        values.put(RANGE, mMyAlarm.getRange());
        values.put(REPEAT, mMyAlarm.getRepeat());
        db.insertOrThrow(TABLE_ALARM, null, values);
        db.close();
    }
    public String getRange(String title)                                  // overriding this function
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query("alarm", null, "TITLE=?", new String[]{title}, null, null, null);
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
                mMyAlarm.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))));
                mMyAlarm.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                mMyAlarm.setTexts(cursor.getString(cursor.getColumnIndex(TEXTS)));
                mMyAlarm.setRange(cursor.getString(cursor.getColumnIndex(RANGE)));
                mMyAlarm.setRepeat(cursor.getString(cursor.getColumnIndex(REPEAT)));
                listAlarm.add(mMyAlarm);
            }while (cursor.moveToNext());
        }
        return listAlarm;
    }

}
