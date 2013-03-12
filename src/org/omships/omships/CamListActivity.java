package org.omships.omships;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CamListActivity extends Activity {
	
	class CamraClicked implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> adaptor, View view, int postion, long id) {
			WebCam item = (WebCam) adaptor.getItemAtPosition(postion);
			Intent intent = new Intent(getApplicationContext(),CamraView.class);
			intent.putExtra("webcam",item);
			startActivity(intent);
		}
	}//end CamraClicked
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camlistlayout);
		Settings.loadConfig(getResources());
		ListView camList = (ListView) findViewById(R.id.camlist);
		ArrayAdapter<WebCam> adapter=new ArrayAdapter<WebCam>(this,android.R.layout.simple_list_item_1,Settings.getShip().getWebcams());
		camList.setAdapter(adapter);
	}//end onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cam_list, menu);
		return true;
	}
}//end CamListActivity
