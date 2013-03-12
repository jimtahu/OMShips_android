package org.omships.omships;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

	    TabHost mTabHost = getTabHost();
	    
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("TAB 1").setContent(R.id.textview1));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("TAB 2").setContent(R.id.textview2));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("TAB 3").setContent(R.id.textview3));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test4").setIndicator("TAB 4").setContent(R.id.textview4));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test5").setIndicator("TAB 5").setContent(R.id.textview5));
	    
	    mTabHost.setCurrentTab(0);
	}

	

}
