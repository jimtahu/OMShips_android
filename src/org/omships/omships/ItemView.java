package org.omships.omships;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;

public class ItemView extends Activity {
	FeedItem item;
	protected WebView webpage;
	protected ImageView viewPic;
	protected FrameLayout placeHolder;
	protected String url;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemviewlayout);
		// Show the Up button in the action bar.
		setupActionBar();
		this.item = getIntent().getExtras().getParcelable("item");
		this.setTitle(this.item.getTitle());
		TextView description = (TextView) findViewById(R.id.description);
		description.setText(item.getDescription());
		url = item.getLink();
		
		description.setVisibility(TextView.VISIBLE);
		initUIpic(url);	
		
	}//end onCreate

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
//	//to preserve webview state
//	@Override
//	protected void onSaveInstanceState(Bundle outState){
//		super.onSaveInstanceState(outState);
//		//Save the state of the WebView
//		webpage.saveState(outState);
//	}
//	
//	@Override
//	protected void onRestoreInstanceState(Bundle savedInstanceState){
//		super.onRestoreInstanceState(savedInstanceState);
//		//restore the state of the WebView
//		webpage.restoreState(savedInstanceState);
//	}
	
	protected void initUIpic(String url){
		placeHolder = ((FrameLayout)findViewById(R.id.webViewPlaceholder));
		if(viewPic == null){
			viewPic = new ImageView(this);
			viewPic.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, 
					LayoutParams.MATCH_PARENT));
			new FetchImage(viewPic).execute(url);
			viewPic.setVisibility(View.VISIBLE);
			viewPic.setContentDescription(item.getDescription());
		}
		placeHolder.addView(viewPic);
	}
	
	//custom handles what happens when the orientation rotates, allowing it to not recall onCreate()
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		if(placeHolder != null){
			placeHolder.removeAllViews();
			super.onConfigurationChanged(newConfig);
			setContentView(R.layout.itemviewlayout);
			if(item.isImage()){
				viewPic.setVisibility(ImageView.VISIBLE);
				initUIpic(url);
			}
		}
	}
	
}

