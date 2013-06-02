package org.omships.omships;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * Class reads RSS data.
 * @author ITCuties
 * From http://www.itcuties.com/android/how-to-write-android-rss-parser/
 */
public class RSSReader implements Reader<FeedItem> {
	// Our class has an attribute which represents RSS Feed URL
    private String rssUrl;
    private int count;
    
    /**
     * We set this URL with the constructor
     */
    public RSSReader(String rssUrl,int count) {
        this.rssUrl = rssUrl;
        this.count = count;
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
        saxParser.parse(rssUrl, handler);
        // The result of the parsing process is being stored in the handler object
        List<FeedItem> items= handler.getItems();
        Collections.sort(items);
        //start to last+1
        return items.subList(0,count+1);
    }
}
