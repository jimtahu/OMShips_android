package org.omships.omships.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A click listener which starts an Activity when an item is clicked.
 * @param <T> A parcelable item to display.
 */
class ItemClicked<T extends Parcelable> implements OnItemClickListener{
	private Activity feedActivity;
	private Class<?> cls;

	/**
	 * @param feedActivity
	 */
	ItemClicked(Activity feedActivity, Class<?> cls) {
		this.feedActivity = feedActivity;
		this.cls = cls;
	}

	@Override
	public void onItemClick(AdapterView<?> adaptor, View view,
			int postion, long id) {
		T item = (T) adaptor.getItemAtPosition(postion);
		Intent intent = new Intent(view.getContext(),cls);
		intent.putExtra("item",item);
		this.feedActivity.startActivity(intent);
	}
}//end class NewsItemClicked