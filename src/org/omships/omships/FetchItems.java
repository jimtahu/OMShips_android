package org.omships.omships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.util.Log;

public class FetchItems extends AsyncTask<Feed,Integer,List<FeedItem> > {
	static Map<String, List<FeedItem> > memo = new HashMap<String, List<FeedItem> >();
	ItemListViewer view;
	
	public static void invalidate(Feed feed){
		if(memo.containsKey(feed.toString()))
			memo.remove(feed.toString());
	}//end invalidate
	
	
	public FetchItems(ItemListViewer view){
		this.view=view;
	}
	
	protected void onPreExecute(){
		//nothing to do here for now
	}
	
	@Override
	protected List<FeedItem> doInBackground(Feed... args) {
		List<FeedItem> items = new ArrayList<FeedItem>();
		for(Feed feed:args){
			List<FeedItem> feed_items=null;
			if(memo.containsKey(feed.toString())){
				feed_items=memo.get(feed.toString());
				Log.e("XMLP","Cache hit on "+feed.toString()
						+" found "+feed_items.size()+" items");
			}else{
				try {
					Log.e("XMLP","Fetching "+feed.toString());
					Reader<FeedItem> reader = feed.getReader();
					feed_items=reader.getItems();
					if(feed_items!=null && feed_items.size()>0)
						memo.put(feed.toString(),feed_items);
		            Log.e("XMLP","Fetch on "+feed.toString()
						+" found "+feed_items.size()+" items");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//end if memo'd
			if(feed_items!=null)items.addAll(feed_items);
		}//end for feeds
		Collections.sort(items);
		return items;
	}//end doInBackground
	
	protected void onPostExecute(List<FeedItem> items){
		try{
			if(this.view==null)return;
			view.setItems(items);
		}catch(java.lang.IllegalArgumentException ex){
			ex.printStackTrace();
		}
	}
}//end class FetchItems
