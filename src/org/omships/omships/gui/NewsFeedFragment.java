package org.omships.omships.gui;

import org.omships.omships.Settings;
import org.omships.omships.datatypes.Feed;

public class NewsFeedFragment extends FeedFragment {
	
	@Override
	Feed[] getFeeds() {
		return Settings.getShip().getNews().toArray(new Feed[Settings.getShip().getNews().size()]);
	}
}
