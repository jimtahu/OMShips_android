package org.omships.omships;

import java.util.List;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PortListFragment extends Fragment implements ItemListViewer {
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle){
		return inflater.inflate(R.layout.camlistlayout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Settings.loadConfig(getResources());
		new FetchItems(this).execute(Settings.getShip().getPorts());
	}//end onStart

	@Override
	public void setItems(List<FeedItem> items) {
		ListView portList = (ListView) getView().findViewById(R.id.camlist);
		FeedArrayAdapter adapter = new FeedArrayAdapter(getActivity(),
				android.R.layout.simple_list_item_1,items);
		portList.setAdapter(adapter);
		portList.setEnabled(false);
	}
}//end CamListActivity
