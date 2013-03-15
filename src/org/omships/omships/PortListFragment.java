package org.omships.omships;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PortListFragment extends Fragment {
		
	
	static class PortsFetch extends AsyncTask<String, Integer, List<FeedItem> >{
		Context context;
		ListView view;		
		
		public PortsFetch(Context context, ListView view) {
			this.context = context;
			this.view = view;
		}

		@Override
		protected List<FeedItem> doInBackground(String... urls) {
			Log.e("XMLP","Fetching ports from "+urls[0]);
			RSSReader reader = new RSSReader(urls[0]);
			List<FeedItem> items=new ArrayList<FeedItem>();
			try {
				items.addAll(reader.getItems());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return items;
		}
		
		protected void onPostExecute(List<FeedItem> items){
			try{
				ArrayAdapter<FeedItem> adapter = new ArrayAdapter<FeedItem>(context,
						android.R.layout.simple_list_item_1,items);
				Log.e("XMLP","Posting portlist of "+items.size()+" ports");
				view.setAdapter(adapter);
			}catch(java.lang.IllegalArgumentException ex){
				ex.printStackTrace();
			}
		}
	}//end class PortsFetch
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle){
		return inflater.inflate(R.layout.camlistlayout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Settings.loadConfig(getResources());
		ListView portList = (ListView) getView().findViewById(R.id.camlist);
		Log.e("XMLP","Asking for portlist");
		new PortsFetch(getActivity(), portList)
		.execute(Settings.getShip().getPorts());
	}//end onStart
}//end CamListActivity
