package org.omships.omships.gui;

import org.omships.omships.Settings;
import org.omships.omships.datatypes.Feed;

public class MediaFeedFragment extends FeedFragment {

	@Override
	Feed[] getFeeds() {
		return Settings.getShip().getMedia().toArray(new Feed[Settings.getShip().getMedia().size()]);
	}
}
