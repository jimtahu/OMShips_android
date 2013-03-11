package org.omships.omships;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			// Create RSS reader
			RSSReader rssReader = new RSSReader(
					"http://www.omships.org/rss/omsi_news.php");
			ListView rssList = (ListView) findViewById(R.id.newslist);
			ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(this,
					android.R.layout.simple_list_item_1,rssReader.getItems());
			rssList.setAdapter(adapter);
		} catch (Exception e) {
			Log.e("OMSHIPS", e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}

}
