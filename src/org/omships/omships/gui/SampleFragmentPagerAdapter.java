package org.omships.omships.gui;

import org.omships.omships.R;
import org.omships.omships.Settings;
import org.omships.omships.parse.FetchLocation;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
	static final String ARG_PAGE = "ARG_PAGE";
	FragmentManager fm;
	Resources resources;
	
    public SampleFragmentPagerAdapter(Resources resources, FragmentManager fm) {
		super(fm);
		this.fm=fm;
		this.resources=resources;
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
    	return resources.getStringArray(R.array.tab_names)[position];
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
}//end SampleFragmentPagerAdapter