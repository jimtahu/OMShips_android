package org.omships.omships;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class VimeoReader implements Reader<FeedItem> {
	private RSSReader reader;
	
	public VimeoReader(String person){
		String url="http://vimeo.com/"+person+"/videos/rss";
		this.reader = new RSSReader(url);
	}
	
	@Override
	public List<FeedItem> getItems() throws ParserConfigurationException, SAXException, IOException{
		List<FeedItem> rssItems=reader.getItems();
		List<FeedItem> items=new ArrayList<FeedItem>();
		for(FeedItem item:rssItems)items.add(new VimeoItem(item));
		return items;
	}
}
