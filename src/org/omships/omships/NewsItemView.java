package org.omships.omships;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

public class NewsItemView extends Activity {
	RSSItem item;
	
	static class FetchImage extends AsyncTask<String, Void, Bitmap>{
		ImageView photo;
		
		public FetchImage(ImageView photo) {
			this.photo=photo;
		}
		
		@Override
		protected Bitmap doInBackground(String...urls) {
			try {
				Bitmap image = BitmapFactory.decodeStream(new URL(urls[0]).openStream());
				return image;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}//end doInBackground
		
		protected void onPostExecute(Bitmap result){
			this.photo.setImageBitmap(result);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemviewlayout);
		// Show the Up button in the action bar.
		setupActionBar();
		this.item = getIntent().getExtras().getParcelable("feeditem");
		this.setTitle(this.item.getTitle());
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(item.getTitle());
		TextView description = (TextView) findViewById(R.id.description);
		description.setText(item.getDescription());
		TextView link = (TextView) findViewById(R.id.link);
		link.setText(item.getLink());
		if(item.getLink().endsWith(".jpg")){
			ImageView photo = (ImageView) findViewById(R.id.photo);
			new FetchImage(photo).execute(item.getLink());
			photo.setVisibility(View.VISIBLE);
			link.setVisibility(View.GONE);
			link.setVisibility(View.GONE);
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

}
