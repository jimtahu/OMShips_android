package org.omships.omships;

import java.util.List;

public interface Reader<T extends FeedItem> {
	public abstract List<T> getItems() throws Exception;
}
