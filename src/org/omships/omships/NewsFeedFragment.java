package org.omships.omships;

public class NewsFeedFragment extends FeedFragment {
	
	@Override
	Feed[] getFeeds() {
		return Settings.getShip().getNews().toArray(new Feed[Settings.getShip().getNews().size()]);
	}
}
