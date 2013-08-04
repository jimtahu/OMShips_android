package org.omships.omships.gui;


import org.omships.omships.R;
import org.omships.omships.datatypes.FeedItem;

import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;

public class VideoView extends Activity {
	FeedItem item;
	protected WebView webpage;
	protected FrameLayout placeHolder;
	protected String url, vidURL;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoviewlayout);
		// Show the Up button in the action bar.
		setupActionBar();
		this.item = getIntent().getExtras().getParcelable("item");
		this.setTitle(this.item.getTitle());
		url = item.getLink();
		
		
		if(url.contains("vimeo")){
			//creates the url for viewing the video directly
			vidURL = item.getLink().substring(17, item.getLink().length());
			vidURL = "http://player.vimeo.com/video/"+vidURL+"?autoplay=1&portrait=0&title&byline";
			initUI(vidURL);
			//enables javascript for the webView
			WebSettings webSettings = webpage.getSettings();
			webSettings.setJavaScriptEnabled(true);			
		}
		
	}//end onCreate

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(false);
		}
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
		placeHolder = ((FrameLayout)findViewById(R.id.vidViewPlaceholder));
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
			super.onConfigurationChanged(newConfig);
			setContentView(R.layout.videoviewlayout);
			initUI(vidURL);
		}
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

