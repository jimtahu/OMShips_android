package org.omships.omships.gui;

import org.omships.omships.R;
import org.omships.omships.Settings;
import org.omships.omships.datatypes.Feed;

import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MediaFeedFragment extends FeedFragment {

	@Override
	Feed[] getFeeds() {
		return Settings.getShip().getMedia().toArray(new Feed[Settings.getShip().getMedia().size()]);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){
		View view = super.onCreateView(inflater, container, SavedInstanceState);
		TextView linkItem = new TextView(getActivity());
		linkItem.setText(R.string.medialink);
		linkItem.setAutoLinkMask(Linkify.ALL);
		linkItem.setTextSize(1.4f*linkItem.getTextSize());
		Linkify.addLinks(linkItem, Linkify.ALL);
		ListView list = (ListView) view.findViewById(R.id.newslist);
		list.addFooterView(linkItem);
		return view;
	}
}
