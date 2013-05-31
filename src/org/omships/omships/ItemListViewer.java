package org.omships.omships;

import java.util.List;

/**
 * @author jimtahu
 * A simple interface for accepting a list of feed items fetched in a background thread.
 */
public interface ItemListViewer {
	/**
	 * Called when the feed items have been fetched. 
	 * @param items A list of FeedItems
	 */
	public void setItems(List<FeedItem> items);
}//end interface
