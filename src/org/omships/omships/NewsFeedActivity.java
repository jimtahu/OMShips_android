package org.omships.omships;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;


public class NewsFeedActivity extends Activity {
	
	private ViewFlipper vf;
	private int mCurrentLayoutState;
	
	class NewsItemClicked implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adaptor, View view,
				int postion, long id) {
			RSSItem item = (RSSItem) adaptor.getItemAtPosition(postion);
			Intent intent = new Intent(getApplicationContext(),NewsItemView.class);
			intent.putExtra("feeditem",item);
			startActivity(intent);
		}
	}//end class NewsItemClicked
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedlayout);
		ListView rssList = (ListView) findViewById(R.id.newslist);
		new FetchItems(this,rssList).execute("http://www.omships.org/rss/omsi_news.php");
		rssList.setOnItemClickListener(new NewsItemClicked());
		vf =(ViewFlipper)findViewById(R.id.view_flipper);
		mCurrentLayoutState = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}
	
//	@Override
//	public boolean onTouchEvent(MotionEvent touchevent){
//		switch (touchevent.getAction())
//		{
//		case MotionEvent.ACTION_DOWN:
//		{
//		lastX = touchevent.getX();
//		break;
//		}
//		case MotionEvent.ACTION_UP:
//		{
//		float currentX = touchevent.getX();
//		if (lastX < currentX)
//		{
//		if (vf.getDisplayedChild()==0) break;
//		vf.setInAnimation(this, R.anim.infromleft);
//		vf.setOutAnimation(this, R.anim.outtoright);
//		vf.showNext();
//		}
//		if (lastX > currentX)
//		{
//		if (vf.getDisplayedChild()==1) break;
//		vf.setInAnimation(this, R.anim.infromright);
//		vf.setOutAnimation(this, R.anim.outtoleft);
//		vf.showPrevious();
//		}
//		break;
//		}
//		}
//		return false;
//	}
	
	/*
     * Switches the layout to the given constant ID as a parameter
     * @param switchTo (should be 0 or a positive number)
     */
	public void switchLayoutStateTo(int switchTo){
        while(mCurrentLayoutState != switchTo){
                if(mCurrentLayoutState > switchTo){
                        mCurrentLayoutState--;
                        vf.setInAnimation(inFromLeftAnimation());
                        vf.setOutAnimation(outToRightAnimation());
                        vf.showPrevious();
                } else {
                        mCurrentLayoutState++;
                        vf.setInAnimation(inFromRightAnimation());
                        vf.setOutAnimation(outToLeftAnimation());
                        vf.showNext();
                }          
               
        };
	}

	/*
	 * Animation methods
	 */
	protected Animation inFromRightAnimation() {
		 
        Animation inFromRight = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, +1.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
	}

	protected Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, -1.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
	}

	protected Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, -1.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
	}

	protected Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, +1.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
	}
}
