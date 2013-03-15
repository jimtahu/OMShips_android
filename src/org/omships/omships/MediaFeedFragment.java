package org.omships.omships;

public class MediaFeedFragment extends FeedFragment {

	@Override
	Feed[] getFeeds() {
		return Settings.getShip().getMedia().toArray(new Feed[Settings.getShip().getMedia().size()]);
	}
}
