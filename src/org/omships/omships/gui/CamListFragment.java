package org.omships.omships.gui;

import org.omships.omships.R;
import org.omships.omships.Settings;
import org.omships.omships.datatypes.WebCam;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Fragment showing the list of webcams.
 */
public class CamListFragment extends Fragment {
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle){
		return inflater.inflate(R.layout.camlistlayout, container, false);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Settings.loadConfig(getResources());
		ListView camList = (ListView) getView().findViewById(R.id.camlist);
		ArrayAdapter<WebCam> adapter=new ArrayAdapter<WebCam>(getActivity(),android.R.layout.simple_list_item_1,Settings.getShip().getWebcams());
		camList.setAdapter(adapter);
		camList.setOnItemClickListener(
				new ItemClicked<WebCam>(getActivity(),CamraView.class));
	}//end onStart
}//end CamListActivity
