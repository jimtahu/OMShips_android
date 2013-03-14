package org.omships.omships;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Shows the current ship on the map of the world.
 * @author jimtahu
 * Based on the basic map demo
 */
public class MapActivity extends FragmentActivity {
	private GoogleMap daMap;
	
	private void setUpMap() {
		LatLng pos = new LatLng(9.9336776461222,102.71869799632);
        daMap.addMarker(new MarkerOptions()
        .position(pos)
        .title(Settings.getShip().getName()));
        daMap.moveCamera(
        		CameraUpdateFactory.newCameraPosition(
        				CameraPosition.fromLatLngZoom(
        						pos,10.0f)));
    }//end setUpMap
	
	private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (daMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            daMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (daMap != null) {
                setUpMap();
            }
        }
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Settings.loadConfig(getResources());
		setContentView(R.layout.maplayout);
		setUpMapIfNeeded();
	}

	protected void onResume(){
		super.onResume();
		setUpMapIfNeeded();
	}
}//end MapActivity
