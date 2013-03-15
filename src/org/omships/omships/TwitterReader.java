package org.omships.omships;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TwitterReader implements Reader<FeedItem> {
	private RSSReader reader;
	
	public TwitterReader(String handle){
		String url="https://api.twitter.com/1/statuses/user_timeline.rss?screen_name="+handle;
		this.reader = new RSSReader(url);
	}
	
	@Override
	public List<FeedItem> getItems() throws ParserConfigurationException, SAXException, IOException{
		List<FeedItem> rssItems=reader.getItems();
		List<FeedItem> items=new ArrayList<FeedItem>();
		for(FeedItem item:rssItems)items.add(new TwitterItem(item));
		return items;
	}

}
