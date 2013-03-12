package org.omships.omships;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maplayout);
		int r=GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(
						getApplicationContext());
		if(r!=ConnectionResult.SUCCESS){
			Toast.makeText(getApplicationContext(), "No google services",Toast.LENGTH_SHORT).show();
			Dialog d=GooglePlayServicesUtil.getErrorDialog(r,this,0);
			d.show();
			return;
		}
		GoogleMapOptions opt=new GoogleMapOptions();
		opt.camera(new CameraPosition(new LatLng(0,0), 0.0f, 0.0f, 0.0f));
		MapView mapview = new MapView(this, opt);
		mapview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MapView mapview = (MapView)v;
				GoogleMap map=mapview.getMap();
				if(map==null){
					Toast.makeText(getApplicationContext(), "No map!",Toast.LENGTH_SHORT).show();
					return;
				}
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);	
			}
		});
		LinearLayout frame = (LinearLayout) findViewById(R.id.mapframe);
		frame.addView(mapview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

}
