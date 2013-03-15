package org.omships.omships;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;

public class ItemView extends Activity {
	RSSItem item;
	WebView webpage;
	
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
		webpage = (WebView) findViewById(R.id.webpage);
		ImageView photo = (ImageView) findViewById(R.id.photo);
		if(item.isImage()){
			new FetchImage(photo).execute(item.getLink());
			photo.setVisibility(View.VISIBLE);
			photo.setContentDescription(item.getDescription());
			webpage.setVisibility(View.GONE);
		}else{
			WebSettings webSettings = webpage.getSettings();
			webSettings.setJavaScriptEnabled(true);
			
			//turns off plugins for all android versions
			if(android.os.Build.VERSION.SDK_INT < 8)
				webpage.getSettings().setPluginsEnabled(false);
			else
				webpage.getSettings().setPluginState(WebSettings.PluginState.OFF);
			//end if/else statement
			
			String vidURL;
			vidURL = item.getLink().substring(17, item.getLink().length());
			if (savedInstanceState == null)
			{
				webpage.loadUrl("http://player.vimeo.com/video/"+vidURL+"?autoplay=1&portrait=0&title&byline");
			}
			
		}//end if image
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
	
	//to preserve webview state
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		//Save the state of the WebView
		webpage.saveState(outState);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		//restore the state of the WebView
		webpage.restoreState(savedInstanceState);
	}

}

