package org.omships.omships.parse;

import java.util.Scanner;

import org.omships.omships.FileBank;
import org.omships.omships.R;
import org.omships.omships.Settings;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;

public class FetchLocation extends AsyncTask<String,Integer,LatLng>{
		private SupportMapFragment map;
		
	public FetchLocation(SupportMapFragment map) {
		this.map=map;
	}
	
	@Override
	protected LatLng doInBackground(String... urls) {
		Double lat=0.0,lng=0.0;
		try {
			Scanner input=new Scanner(FileBank.openStream(urls[0]));
			input.useDelimiter(",");
			//unix time
			input.next();
			//lng then lat (backwards)
			lng=input.nextDouble();
			lat=input.nextDouble();
			input.close();
			return new LatLng(lat, lng);
		} catch (Exception ex) {
			Log.e("LOCATION",ex.toString(),ex);
			FileBank.invalidate(urls[0]);
			return null;
		}
	}//end doInBackground
	
	protected void onPostExecute(LatLng pos){
		GoogleMap daMap =map.getMap(); 
		if(daMap==null)return;
		if(pos==null){
			Dialog error = new Dialog(map.getActivity());
			error.setTitle(R.string.location_fail_message);
			error.setCancelable(true);
			error.setCanceledOnTouchOutside(true);
			error.show();
			return;
		}
		daMap.addMarker(new MarkerOptions()
        .position(pos)
        .title(Settings.getShip().getName()));
        daMap.moveCamera(
        		CameraUpdateFactory.newCameraPosition(
        				CameraPosition.fromLatLngZoom(
        						pos,5.0f)));
	}//end onPostExecute
	
}//end class FetchLocation