package com.iitkgp.gaurav.geoalarm;



/**
 * Created by gaurav on 6/23/2015.
 */
public class ListViewItem {
    int iconId;
    String alarmTitle;
    String distance;

    public ListViewItem(String title,String area,int id){
        iconId = id;
        alarmTitle = title;
        distance = area;
    }
}
