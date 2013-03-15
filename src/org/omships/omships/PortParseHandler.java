package org.omships.omships;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX tag handler for pulling ports from rss.
 * @author jimtahu
 */
public class PortParseHandler extends DefaultHandler {
	// List of items parsed
    private List<FeedItem> ports;

    public PortParseHandler() {
        ports = new ArrayList<FeedItem>();
    }
    // We have an access method which returns a list of items that are read from the RSS feed. This method will be called when parsing is done.
    public List<FeedItem> getItems() {
        return ports;
    }
    // The StartElement method creates an empty RssItem object when an item start tag is being processed. When a title or link tag are being processed appropriate indicators are set to true.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("port".equals(qName)) {
        	PortItem port = new PortItem();
        	port.setConfirmed(attributes.getValue("confirmed"));
        	port.setCity(attributes.getValue("city"));
        	port.setCountry(attributes.getValue("country"));
        	port.setArrive(attributes.getValue("arrival"));
        	port.setDepart(attributes.getValue("departure"));
        	ports.add(port);
        }
    }//end startElement
}//end class
