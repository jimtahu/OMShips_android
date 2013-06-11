package org.omships.omships.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.omships.omships.datatypes.Feed;
import org.omships.omships.datatypes.FeedItem;
import org.omships.omships.datatypes.TwitterItem;
import org.xml.sax.SAXException;

public class TwitterReader implements Reader<FeedItem> {
	private RSSReader reader;
	
	public TwitterReader(Feed input){
		this.reader = new RSSReader(input);
	}
	
	@Override
	public List<FeedItem> getItems() throws ParserConfigurationException, SAXException, IOException{
		List<FeedItem> rssItems=reader.getItems();
		List<FeedItem> items=new ArrayList<FeedItem>();
		for(FeedItem item:rssItems)items.add(new TwitterItem(item));
		return items;
	}

}
