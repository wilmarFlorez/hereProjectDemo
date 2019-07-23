package com.wilmarflorez.hereprojectdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.SupportMapFragment;

public class MainActivity extends AppCompatActivity {


    private Map map = null;
    private SupportMapFragment mapFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }

    private void initialize() {
        setContentView(R.layout.activity_main);
        // Search for the map fragment to finish setup by calling init().
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    // retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();
                    // Set the map center to the Vancouver region (no animation)
                    map.setCenter(new GeoCoordinate(49.196261, -123.004773, 0.0),
                            Map.Animation.NONE);
                    // Set the zoom level to the average between min and max
                    map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                } else {
                    System.out.println("ERROR: Cannot initialize Map Fragment");
                }
            }
        });
    }
}
