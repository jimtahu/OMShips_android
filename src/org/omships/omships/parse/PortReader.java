package org.omships.omships.parse;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.omships.omships.datatypes.Feed;
import org.omships.omships.datatypes.FeedItem;
import org.xml.sax.SAXException;

/**
 * Class reads port data.
 * @author jimtahu
 */
public class PortReader implements Reader<FeedItem> {
    private Feed feed;

    public PortReader(Feed input) {
        this.feed = input;
    }
    
    public List<FeedItem> getItems() throws ParserConfigurationException, SAXException, IOException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PortParseHandler handler = new PortParseHandler();
        saxParser.parse(feed.getStream(), handler);
        // The result of the parsing process is being stored in the handler object
        return handler.getItems();
    }
}
