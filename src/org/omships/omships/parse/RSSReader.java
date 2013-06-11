package org.omships.omships.parse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.omships.omships.datatypes.Feed;
import org.omships.omships.datatypes.FeedItem;
import org.xml.sax.SAXException;

/**
 * Class reads RSS data.
 * @author ITCuties
 * From http://www.itcuties.com/android/how-to-write-android-rss-parser/
 */
public class RSSReader implements Reader<FeedItem> {
    private Feed feed;
    
    /**
     * We set this URL with the constructor
     */
    public RSSReader(Feed input) {
        this.feed = input;
    }
    /**
     * Get RSS items. This method will be called to get the parsing process result.
     * @return
     * @throws SAXException 
     * @throws ParserConfigurationException 
     * @throws IOException 
     */
    public List<FeedItem> getItems() throws ParserConfigurationException, SAXException, IOException{
        // At first we need to get an SAX Parser Factory object
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // Using factory we create a new SAX Parser instance
        SAXParser saxParser = factory.newSAXParser();
        // We need the SAX parser handler object
        RssParseHandler handler = new RssParseHandler();
        // We call the method parsing our RSS Feed
        saxParser.parse(feed.getStream(), handler);
        // The result of the parsing process is being stored in the handler object
        List<FeedItem> items= handler.getItems();
        Collections.sort(items);
        //start to last+1
        return items.subList(0,feed.getMax()+1);
    }
}
