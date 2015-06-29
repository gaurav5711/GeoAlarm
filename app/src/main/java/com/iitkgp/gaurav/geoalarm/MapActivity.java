package com.iitkgp.gaurav.geoalarm;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.gms.maps.GoogleMap;


public class MapActivity extends Activity {

    GoogleMap googleMap;
    Marker oldMarker;
    View touchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        touchView = findViewById(R.id.touchView);
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("MapActivity","::"+event.getX()+",,"+event.getY());
                return false;
            }
        });
        createMapView();
        googleMap.setMyLocationEnabled(true);     // Used to display present location icon on application
//        Location mLocation = googleMap.getMyLocation();
//        if(mLocation!=null) {
//            Toast.makeText(this, " " + mLocation.getLatitude() + ",," + mLocation.getLongitude(), Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this, "Null value", Toast.LENGTH_SHORT).show();
//        }
        oldMarker=googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)));
        oldMarker.setVisible(false);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Double lat = latLng.longitude;
                Toast.makeText(MapActivity.this, " " + latLng.latitude + ",," + latLng.longitude+"", Toast.LENGTH_SHORT).show();
                oldMarker.remove();
                Marker mMarker=googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)));
                oldMarker=mMarker;
                Bundle bundle = new Bundle();
                bundle.putDouble("latitude",latLng.latitude);
                bundle.putDouble("longitude",latLng.longitude);
                Intent intent = new Intent (MapActivity.this,AlarmDetail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        addMarker();
    }


    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }


    /**
     * Adds a marker to the map
     */
    private void addMarker(){

        /** Make sure that the map has been initialised **/
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(0, 0))
                            .title("Marker")
                            .draggable(true)
            );
        }
    }



}
