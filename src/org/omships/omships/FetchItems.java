package org.omships.omships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.util.Log;

/**
 * This class Async fetches the items for a feed and posts it to an ItemListViewer.
 * Once fetched, feeds are cached in a static memo based on url
 *  and not redownloaded unless they are invalidated.
 * This means that feeds will be fetched only once per application run.
 */
public class FetchItems extends AsyncTask<Feed,Integer,List<FeedItem> > {
	static Map<String, List<FeedItem> > memo = new HashMap<String, List<FeedItem> >();
	ItemListViewer view;
	
	/**
	 * Removes a feed from the cache.
	 * @param url
	 * This removes a feed from the local cache.
	 * This should not have any negative side effects on existing
	 *  usages of the feed, but forces it to be redownloaded
	 *  the next time it is called for.
	 */
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
	
	/**
	 * Downloads the given feeds.
	 * If more than one feed is asked for, they will be combined.
	 * Items are sorted before they are returned.
	 */
	@Override
	protected List<FeedItem> doInBackground(Feed... args) {
		List<FeedItem> items = new ArrayList<FeedItem>();
		for(Feed feed:args){
			List<FeedItem> feed_items=null;
			if(memo.containsKey(feed.toString())){
				feed_items=memo.get(feed.toString());
				Log.i("XMLP","Cache hit on "+feed.toString()
						+" found "+feed_items.size()+" items");
			}else{
				try {
					Log.i("XMLP","Fetching "+feed.toString());
					Reader<FeedItem> reader = feed.getReader();
					feed_items=reader.getItems();
					if(feed_items!=null && feed_items.size()>0)
						memo.put(feed.toString(),feed_items);
		            Log.i("XMLP","Fetch on "+feed.toString()
						+" found "+feed_items.size()+" items");
				} catch (Exception ex) {
					Log.e("XMLP", ex.toString(), ex);
				}
			}//end if memo'd
			if(feed_items!=null)items.addAll(feed_items);
		}//end for feeds
		Collections.sort(items);
		return items;
	}//end doInBackground
	
	protected void onPostExecute(List<FeedItem> items){
		if(this.view==null)return;
		view.setItems(items);
	}
}//end class FetchItems
