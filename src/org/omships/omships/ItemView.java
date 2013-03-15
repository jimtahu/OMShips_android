package org.omships.omships;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;

public class ItemView extends Activity {
	RSSItem item;
	protected WebView webpage;
	protected FrameLayout placeHolder;
	protected String url, vidURL;
	
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
		
		//decides what to do depending on whether or not the item is an image or video
		if(item.isImage()){
			ImageView photo = (ImageView) findViewById(R.id.photo);
			new FetchImage(photo).execute(url);
			photo.setVisibility(View.VISIBLE);
			photo.setContentDescription(item.getDescription());
		}else if(url.contains("vimeo")){
			//creates the url for viewing the video directly
			vidURL = item.getLink().substring(17, item.getLink().length());
			vidURL = "http://player.vimeo.com/video/"+vidURL+"?autoplay=1&portrait=0&title&byline";
			initUI(vidURL);
			//enables javascript for the webView
			WebSettings webSettings = webpage.getSettings();
			webSettings.setJavaScriptEnabled(true);			
		}else{
			webpage = (WebView)(findViewById(R.id.web_view));
			webpage.loadUrl(item.getLink());
		}
		
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
	
	//puts video in frameLayout
	protected void initUI(String vidURL){
		placeHolder = ((FrameLayout)findViewById(R.id.webViewPlaceholder));
		if(webpage == null){
			webpage = new WebView(this);
			webpage.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, 
					LayoutParams.MATCH_PARENT));
			webpage.setWebViewClient(new WebViewClient());
			webpage.loadUrl(vidURL);
		}
		placeHolder.addView(webpage);
	}
	
	//custom handles what happens when the orientation rotates, allowing it to not recall onCreate()
	@Override
	public void onConfigurationChanged(Configuration newConfig){
		if(webpage != null){
			placeHolder.removeView(webpage);
		}
		
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.itemviewlayout);
		initUI(vidURL);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(this.url.contains("vimeo")){
			webpage.stopLoading(); 
			webpage.loadUrl("");
			webpage.reload();
			webpage = null;
		}
	}
}

