package org.omships.omships;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FetchItems extends AsyncTask<Feed,Integer,Integer> {
	Context context;
	ListView view;
	List<RSSItem> items;
	
	public FetchItems(Context context,ListView view){
		this.context=context;
		this.view=view;
	}
	
	@Override
	protected Integer doInBackground(Feed... args) {
		items = new ArrayList<RSSItem>();
		for(Feed feed:args){
			if(!feed.getType().equals("rss"))continue;
			RSSReader rssReader = new RSSReader(feed.getUrl());
			try {
				items.addAll(rssReader.getItems());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//end for feeds
		return items.size();
	}//end doInBackground
	
	protected void onPostExecute(Integer count){
		ArrayAdapter<RSSItem> adapter = new ArrayAdapter<RSSItem>(context,
				android.R.layout.simple_list_item_1,items);
		view.setAdapter(adapter);
	}
}//end class FetchItems
