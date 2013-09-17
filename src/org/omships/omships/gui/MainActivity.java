package org.omships.omships.gui;
 
import org.omships.omships.FileBank;
import org.omships.omships.R;
import org.omships.omships.Settings;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
 
public class MainActivity extends FragmentActivity {
    MenuItem mapFlip;
    SampleFragmentPagerAdapter adapter;
    
	/**
	 * Called when the app is loading (in an atempt to preload some items).
	 */
	protected void startPreload(){
		Settings.loadConfig(getResources());
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileBank.setContext(getApplicationContext());
        FileBank.cleanOldFiles();
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        
        //checks for cellular or wifi internet access, then stops the application if none exists
        if(!haveNetworkConnection()){
        	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        	builder.setTitle(R.string.alert_title);
        	builder.setMessage(R.string.connection_alert);
        	builder.setCancelable(false);
        		
        	builder.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                	Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
                }
            });
        	AlertDialog noConnAlert = builder.create();
        	noConnAlert.show();
        	return;
        }
        int playServiceResults = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if( playServiceResults != ConnectionResult.SUCCESS){
        	 GooglePlayServicesUtil.getErrorDialog(playServiceResults, this, 69).show();
        }
    	startPreload();
    	adapter=new SampleFragmentPagerAdapter(this.getResources(), getSupportFragmentManager());
    	viewPager.setAdapter(adapter);
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.mapFlip=menu.add(R.string.flip_map);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == mapFlip.getItemId())
		{
			adapter.flipMapType();
		}
		return super.onOptionsItemSelected(item);
	}
    
    //code segment take from Squonk on stackoverflow at 
    //http://stackoverflow.com/questions/4238921/android-detect-whether-there-is-an-internet-connection-available
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}