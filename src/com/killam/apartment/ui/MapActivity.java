/**
 * 
 */
package com.killam.apartment.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.killam.apartment.R;


/**
 * @author Farhan
 *
 */

/*public class MapActivity extends FragmentActivity {
	 
    // Google Map
    private GoogleMap googleMap;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
 
        try {
            // Loading map
            //initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    *//**
     * function to load map. If map is not created it will create it for you
     * *//*
    private void initilizeMap() {
    	
    	 
    	
        if (googleMap == null) {
        	
        	FragmentManager fmanager = getSupportFragmentManager();
	        Fragment fragment = fmanager.findFragmentById(R.id.map);
	        SupportMapFragment supportmapfragment = (SupportMapFragment)fragment;
	        googleMap = supportmapfragment.getMap();
        	
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        //initilizeMap();
    }
 
}
*/

public class MapActivity extends BaseActivity {
	 
    // Google Map
    private GoogleMap googleMap;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setActionBar(R.string.app_name, true, false, false, false);
 
        try {
            // Loading map
            //initilizeMap();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 
}
