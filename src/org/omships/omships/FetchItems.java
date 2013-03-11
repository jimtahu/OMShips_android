package org.omships.omships;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FetchItems extends AsyncTask<String,Integer,Integer> {
	Context context;
	ListView view;
	List<RSSItem> items;
	
	public FetchItems(Context context,ListView view){
		this.context=context;
		this.view=view;
	}
	
	@Override
	protected Integer doInBackground(String... args) {
		RSSReader rssReader = new RSSReader("http://www.omships.org/rss/omsi_news.php");
		try {
			items = rssReader.getItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items.size();
	}//end doInBackground
	
	protected void onPostExecute(Integer count){
		ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(context,
				android.R.layout.simple_list_item_1,items);
		view.setAdapter(adapter);
	}
}//end class FetchItems
