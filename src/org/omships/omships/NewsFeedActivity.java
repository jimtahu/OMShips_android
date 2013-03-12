package org.omships.omships;

public class NewsFeedActivity extends FeedActivity {
	
	@Override
	Feed[] getFeeds() {
		return Settings.getShip().getNews().toArray(new Feed[Settings.getShip().getNews().size()]);
	}
}
