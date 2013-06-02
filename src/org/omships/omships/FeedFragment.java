package org.omships.omships;

import java.util.List;

import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

/**
 * Base class for the news and meadia feeds.
 * @author jimtahu
 *
 */
public abstract class FeedFragment extends Fragment implements ItemListViewer {
	
	/**
	 * Gets the list of feeds for this implementation.
	 * @return
	 */
	abstract Feed[] getFeeds(); 
	
	/**
	 * Handles feed items being clicked.
	 * Starts an intent based on the item clicked.
	 */
	class NewsItemClicked implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adaptor, View view,
				int postion, long id) {
			FeedItem item = (FeedItem) adaptor.getItemAtPosition(postion);
			Intent intent;
			if(item.isVideo())
				intent = new Intent(getActivity().getApplicationContext(), VideoView.class);
			else if(item.isWebPage())
				intent = new Intent(getActivity().getApplicationContext(), WebPageView.class);
			else
				intent = new Intent(getActivity().getApplicationContext(),ItemView.class);
			intent.putExtra("item",item);
			startActivity(intent);
		}
	}//end class NewsItemClicked
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
		return inflater.inflate(R.layout.feedlayout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Settings.loadConfig(getResources());
		ListView rssList = (ListView) getView().findViewById(R.id.newslist);
		new FetchItems(this).execute(getFeeds());
		rssList.setOnItemClickListener(new NewsItemClicked());
	}

	/**
	 * Callback for setting the items in the feed.
	 * @param items 
	 * This is called by the item fetcher. 
	 */
	public void setItems(List<FeedItem> items){
		ListView rssList = (ListView) getView().findViewById(R.id.newslist);
		FeedArrayAdapter adapter = new FeedArrayAdapter(getActivity(),
				android.R.layout.simple_list_item_1,items);
		rssList.setAdapter(adapter);
		TextView waitmark = (TextView) getView().findViewById(R.id.waitmark);
		waitmark.setVisibility(View.GONE);
	}
	
}//end FeedFragment
