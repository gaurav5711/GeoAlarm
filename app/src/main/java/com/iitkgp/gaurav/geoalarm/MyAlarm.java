package com.iitkgp.gaurav.geoalarm;

/**
 * Created by gaurav on 6/26/2015.
 */
public class MyAlarm {
  //  int id;
    String title;
    String texts;
    String range;
    String repeat;

    public MyAlarm(){}

    public MyAlarm(String mTitle,String mTexts,String mRange,String mRepeat){
 //       id = mId;
        title= mTitle;
        texts= mTexts;
        range = mRange;
        repeat = mRepeat;
    }

 //   public void setId(int mId){
 //       id = mId;
 //   }
 //   public int getId(){
 //       return  id;
 //   }

    public void setTitle(String mTitle){
        title = mTitle;
    }
    public String getTitle(){
        return title;
    }

    public void setTexts(String mText){
        texts = mText;
    }
    public String getTexts(){
        return  texts;
    }

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
}
