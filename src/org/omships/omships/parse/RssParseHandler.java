package org.omships.omships.parse;

import java.util.ArrayList;
import java.util.List;

import org.omships.omships.datatypes.FeedItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX tag handler. The Class contains a list of RssItems which is being filled while the parser is working
 * @author ITCuties (extended by jimtahu)
 * from http://www.itcuties.com/android/how-to-write-android-rss-parser/
 */
public class RssParseHandler extends DefaultHandler {
	private enum SubItem {
		none,
		title,
		link,
		date,
		description,
	}
	
    // List of items parsed
    private List<FeedItem> rssItems;
    // We have a local reference to an object which is constructed while parser is working on an item tag
    // Used to reference item while parsing
    private FeedItem currentItem;
    
    private SubItem currentSub;
    
    private StringBuffer currentString;
 
    public RssParseHandler() {
        rssItems = new ArrayList<FeedItem>();
        currentSub = SubItem.none;
    }
    // We have an access method which returns a list of items that are read from the RSS feed. This method will be called when parsing is done.
    public List<FeedItem> getItems() {
        return rssItems;
    }
    // The StartElement method creates an empty RssItem object when an item start tag is being processed. When a title or link tag are being processed appropriate indicators are set to true.
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	currentString = new StringBuffer();
        if ("item".equals(qName)) {
            currentItem = new FeedItem();
            currentSub = SubItem.none;
        } else if ("title".equals(qName)) {
            currentSub = SubItem.title;
        } else if ("link".equals(qName)) {
            currentSub = SubItem.link;
        } else if ("description".equals(qName)){
        	currentSub = SubItem.description;
        } else if ("pubDate".equals(qName)){
        	currentSub = SubItem.date;
        }
    }
    // The EndElement method adds the  current RssItem to the list when a closing item tag is processed. It sets appropriate indicators to false -  when title and link closing tags are processed
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (currentSub) {
		case title:
			if (currentItem != null)
				currentItem.setTitle(currentString.toString());
			break;
		case link:
			if (currentItem != null)
				currentItem.setLink(currentString.toString());
			break;
		case description:
			if (currentItem != null)
				currentItem.setDescription(currentString.toString());
			break;
		case date:
			if (currentItem != null)
				currentItem.setPubDate(currentString.toString());
		default:
			break;
		}
		currentString = null;
		if ("item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		}
		currentSub = SubItem.none;
    }
    // Characters method fills current RssItem object with data when title and link tag content is being processed
    @Override
    public void characters(char[] ch, int start, int length){
    	if(currentString!=null)
    		currentString.append(new String(ch, start, length));
    }//end characters
}//end class
