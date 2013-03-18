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
import android.os.Build;

public class ItemView extends Activity {
	FeedItem item;
	protected WebView webpage;
	protected ImageView viewPic;
	protected FrameLayout placeHolder;
	protected String url;
	protected TextView description;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemviewlayout);
		// Show the Up button in the action bar.
		setupActionBar();
		this.item = getIntent().getExtras().getParcelable("item");
		this.setTitle(this.item.getTitle());
		description = (TextView) findViewById(R.id.description);
		description.setText(item.getDescription());
		url = item.getLink();
		
		description.setVisibility(TextView.VISIBLE);	
		placeHolder = ((FrameLayout)findViewById(R.id.webViewPlaceholder));
		viewPic = new ImageView(this);
		viewPic.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT));
		new FetchImage(viewPic).execute(url);
		viewPic.setVisibility(View.VISIBLE);
		viewPic.setContentDescription(item.getDescription());
		placeHolder.addView(viewPic);
		
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
	
	protected void initUIpic(String url){
		description.setVisibility(TextView.VISIBLE);
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
	
}

