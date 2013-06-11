package org.omships.omships.parse;

import java.util.List;

import org.omships.omships.datatypes.FeedItem;

public interface Reader<T extends FeedItem> {
	public abstract List<T> getItems() throws Exception;
}
