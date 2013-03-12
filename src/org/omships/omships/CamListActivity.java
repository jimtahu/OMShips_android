package org.omships.omships;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CamListActivity extends FragmentActivity {
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camlistlayout);
		Settings.loadConfig(getResources());
		ListView camList = (ListView) findViewById(R.id.camlist);
		ArrayAdapter<WebCam> adapter=new ArrayAdapter<WebCam>(this,android.R.layout.simple_list_item_1,Settings.getShip().getWebcams());
		camList.setAdapter(adapter);
		camList.setOnItemClickListener(
				new ItemClicked<WebCam>(this,CamraView.class));
	}//end onCreate

}//end CamListActivity
