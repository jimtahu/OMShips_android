package org.omships.omships;
 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class MainActivity extends FragmentActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter());
    }
 
    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 5;
 
        public SampleFragmentPagerAdapter() {
            super(getSupportFragmentManager());
        }
 
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
 
        @Override
        public Fragment getItem(int position) {
        	return PageFragment.create(position + 1);
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
            	return "Map";}
            else if(position == 1){
            	return "News/PrayerRequests";}
            else if(position == 2){
            	return "Media";}
            else if(position == 3){
            	return "Webcams";}
            else{
            	return "Port Schedule";}
        }
    }
 
    public static class PageFragment extends Fragment {
        public static final String ARG_PAGE = "ARG_PAGE";
 
        public static Fragment create(int page) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            Fragment fragment;
            if(page!=4)
            	fragment = new PageFragment();
            else
            	fragment = new CamListFragment();
            fragment.setArguments(args);
            return fragment;
        }
 
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