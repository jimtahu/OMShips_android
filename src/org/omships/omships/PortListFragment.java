package org.omships.omships;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PortListFragment extends Fragment {
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle){
		return inflater.inflate(R.layout.camlistlayout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Settings.loadConfig(getResources());
		ListView portList = (ListView) getView().findViewById(R.id.camlist);
		new FetchItems(getActivity(),portList).execute(Settings.getShip().getPorts());
	}//end onStart
}//end CamListActivity
