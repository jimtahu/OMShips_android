package org.omships.omships.gui;


import org.omships.omships.R;
import org.omships.omships.datatypes.FeedItem;

import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;

public class WebPageView extends Activity {
	FeedItem item;
	protected WebView webpage;
	protected String url;
	ProgressBar progressBar;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webviewlayout);
		// Show the Up button in the action bar.
		setupActionBar();
		
		this.item = getIntent().getExtras().getParcelable("item");
		this.setTitle(this.item.getTitle());
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		url = item.getLink();
		webpage = new WebView(this);
		webpage = (WebView)(findViewById(R.id.web_view));
		if(url.contains("twitter"))
			url.replace("http://", "https://mobile.");
		webpage.setWebViewClient(new WebViewClient(){
			@Override
	        public void onPageStarted(WebView view, String url, Bitmap favicon) {
	            // TODO Auto-generated method stub
	            super.onPageStarted(view, url, favicon);
	        }
	 
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            // TODO Auto-generated method stub
	        	view.loadUrl(url);
	            return true;
	        }
	 
	        @Override
	        public void onPageFinished(WebView view, String url) {
	            // TODO Auto-generated method stub
	            super.onPageFinished(view, url);
	            progressBar.setVisibility(View.GONE);
	        }});
		
		webpage.getSettings().setJavaScriptEnabled(true);
		webpage.getSettings().setDomStorageEnabled(true);
		webpage.loadUrl(url);
		webpage.setVisibility(WebView.VISIBLE);
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
}

