package com.iitkgp.gaurav.geoalarm;

import org.apache.http.entity.StringEntity;

/**
 * Created by gaurav on 6/26/2015.
 */
public class MyAlarm {
  //  int id;
    String title;
    String texts;
    String range;
    String repeat;
    String mLatitude;
    String mLongitude;

    public MyAlarm(){}

    public MyAlarm(String mTitle,String mTexts,String mRange,String latitude,String longitude,String mRepeat){
 //       id = mId;
        title= mTitle;
        texts= mTexts;
        range = mRange;
        repeat = mRepeat;
        mLatitude=latitude;
        mLongitude=longitude;
    }

 //   public void setId(int mId){
 //       id = mId;
 //   }
 //   public int getId(){
 //       return  id;
 //   }

    public void setTitle(String mTitle){title = mTitle;}
    public String getTitle(){return title;}

    public void setTexts(String mText){texts = mText;}
    public String getTexts(){return  texts;}

    public void setRange(String mRange){
        range = mRange;
    }
    public String getRange(){
        return  range;
    }

    public void setRepeat(String mRepeat){
        repeat = mRepeat;
    }
    public String getRepeat(){
        return  repeat;
    }

    public void setLatitude(String latitude){mLatitude=latitude;}
    public String getLatitude(){return mLatitude;}

    public void setLongitude(String longitude){mLongitude=longitude;}
    public String getLongitude(){return mLongitude;}
}
