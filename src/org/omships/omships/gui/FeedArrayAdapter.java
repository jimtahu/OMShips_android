package org.omships.omships.gui;

import java.util.List;

import org.omships.omships.R;
import org.omships.omships.datatypes.FeedItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Constructs slightly more interesting things than TextViews for the list.
 * @author jimtahu
 * These are used to convert FeedItem lists into Views describing the items,
 * which are then placed in the list.
 */
public class FeedArrayAdapter extends ArrayAdapter<FeedItem> {
	private LayoutInflater inflater;
	
	/**
	 * 
	 * @param context
	 * @param textViewResourceId
	 * @param objects FeedItems to display.
	 */
	public FeedArrayAdapter(Context context, int textViewResourceId,
			List<FeedItem> objects) {
		super(context, textViewResourceId, objects);
		inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * This returns the view created for a given object.
	 * It is called by our super while it is working, do not use independantly.
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FeedItem item = getItem(position);
		convertView = inflater.inflate(R.layout.feedline_layout,null);
		//ImageView iv = (ImageView) convertView.findViewById(R.id.icon_value);
		TextView tv = (TextView) convertView.findViewById(R.id.text_value);
		//iv.setImageResource(item.getImageResource());
		tv.setText(item.toString());
		return convertView;
	}//end getView

}//end class FeedArrayAdapter
