package org.omships.omships;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MediaFeedActivity extends Activity {
	
	class NewsItemClicked implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adaptor, View view,
				int postion, long id) {
			RSSItem item = (RSSItem) adaptor.getItemAtPosition(postion);
			Intent intent = new Intent(getApplicationContext(),NewsItemView.class);
			intent.putExtra("feeditem",item);
			startActivity(intent);
		}
	}//end class NewsItemClicked
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedlayout);
		ListView rssList = (ListView) findViewById(R.id.newslist);
		new FetchItems(this,rssList).execute(
				Settings.getShip().getMedia().toArray(new Feed[Settings.getShip().getMedia().size()])
				);
		rssList.setOnItemClickListener(new NewsItemClicked());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}

}
