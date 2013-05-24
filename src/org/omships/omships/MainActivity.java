package org.omships.omships;
 
import java.util.ArrayList;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.SupportMapFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class MainActivity extends FragmentActivity {
	
	protected void startPreload(){
		Settings.loadConfig(getResources());
		ArrayList<Feed> feedList = new ArrayList<Feed>();
		feedList.addAll(Settings.getShip().getNews());
		feedList.addAll(Settings.getShip().getMedia());
		feedList.add(Settings.getShip().getPorts());
		new FetchItems(this,null)
		.execute(feedList.toArray(new Feed[feedList.size()]));
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    	viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
        
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
 
    static public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    	static final String ARG_PAGE = "ARG_PAGE";
    	static final String[] tabNames =
    		{"News/PrayerRequests", "Media","Webcams","Port Schedule", "Location"};
    	FragmentManager fm;
    	
        public SampleFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			this.fm=fm;
		}

		static final int PAGE_COUNT = 5;
        private SparseArray<Fragment> frags =
        		new SparseArray<Fragment>(PAGE_COUNT);        
 
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
 
        @Override
        public Fragment getItem(int position) {
        	if(frags.get(position)==null)
        		frags.put(position, create(position));
        	return frags.get(position);
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
        	return tabNames[position];
        }
        
        public SupportMapFragment setupMap(){
        	
        	SupportMapFragment fragment = new SupportMapFragment();
        	new FetchLocation(fragment)
        		.execute(Settings.getShip().getLocation());
        	return fragment;
        }
        
        public Fragment create(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            Fragment fragment;
            switch(page){
            case 0:
            	fragment = new NewsFeedFragment();
            	break;
            case 1:
            	fragment = new MediaFeedFragment();
            	break;
            case 2:
            	fragment = new CamListFragment();
            	break;
            case 3:
            	fragment = new PortListFragment();
            	break;
            case 4:
            	fragment = setupMap();
            	break;
            default:
            	fragment = new PageFragment();
            }//end switch
            fragment.setArguments(args);
            return fragment;
        }//end create
    }//end SampleFragmentPagerAdapter
 
    public static class PageFragment extends Fragment {
  
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
 
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_page, container, false);
        }
    }
}