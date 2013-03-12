package org.omships.omships;

public class MediaFeedActivity extends FeedActivity {

	@Override
	Feed[] getFeeds() {
		return Settings.getShip().getMedia().toArray(new Feed[Settings.getShip().getMedia().size()]);
	}
}
