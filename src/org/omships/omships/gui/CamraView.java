package org.omships.omships.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.omships.omships.FileBank;
import org.omships.omships.R;
import org.omships.omships.datatypes.WebCam;
import org.omships.omships.parse.FetchImage;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.os.Build;

/**
 * Activity displaying a webcam.
 * It basically just shows the image from the cam and periodically updates.
 */
public class CamraView extends Activity {
	WebCam camera;
	ImageView photo;
	
	/**
	 * creates new FetchImage (to be used on UI thread).
	 */
	private Runnable runUpdate = new Runnable() {
		@Override
		public void run() {
			FileBank.invalidate(camera.getUrl());
			new FetchImage(photo).execute(camera.getUrl());
		}
	};
	
	/**
	 * Runs update request on UI thread.
	 */
	protected void TimerClick(){
		this.runOnUiThread(runUpdate);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camraviewlayout);
		// Show the Up button in the action bar.
		setupActionBar();
		camera=getIntent().getExtras().getParcelable("item");
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(camera.getName());
		photo = (ImageView) findViewById(R.id.photo);
		new FetchImage(photo).execute(camera.getUrl());
		Timer updateTimer = new Timer();
		updateTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				TimerClick();
			}
			//camera is in seconds, and timer is in mS
		}, camera.getUpdate()*1000, camera.getUpdate()*1000);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(false);
		}
	}

}
