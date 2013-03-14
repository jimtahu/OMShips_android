package org.omships.omships;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.support.v4.app.FragmentActivity;

public abstract class FeedActivity extends FragmentActivity {
	
	abstract Feed[] getFeeds(); 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedlayout);
		Settings.loadConfig(getResources());
		ListView rssList = (ListView) findViewById(R.id.newslist);
		new FetchItems(this,rssList).execute(getFeeds());
		rssList.setOnItemClickListener(
				new ItemClicked<RSSItem>(this,ItemView.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}

}
