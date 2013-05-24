package org.omships.omships;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Constructs slightly more interesting things than TextViews for the list.
 * @author jimtahu
 *
 */
public class FeedArrayAdapter extends ArrayAdapter<FeedItem> {
	private LayoutInflater inflater;
	
	public FeedArrayAdapter(Context context, int textViewResourceId,
			List<FeedItem> objects) {
		super(context, textViewResourceId, objects);
		inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FeedItem item = getItem(position);
		convertView = inflater.inflate(R.layout.feedline_layout,null);
		//ImageView iv = (ImageView) convertView.findViewById(R.id.icon_value);
		TextView tv = (TextView) convertView.findViewById(R.id.text_value);
		//iv.setImageResource(item.getImageResource());
		tv.setText(item.toString());
		return convertView;
	}

}//end class FeedArrayAdapter
