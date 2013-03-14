package org.omships.omships;

import java.io.IOException;
import java.io.InputStream;
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
public class SettingsReader {
	// Our class has an attribute which represents RSS Feed URL
    private String configUrl;
    private InputStream input;
    /**
     * We set this URL with the constructor
     */
    public SettingsReader(String url) {
        this.configUrl = url;
    }
    /**
     * We set the input with the constructor
     */
    public SettingsReader(InputStream in) {
        this.input = in;
    }
    
    public List<Ship> getItems() throws ParserConfigurationException, SAXException, IOException {
        // At first we need to get an SAX Parser Factory object
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // Using factory we create a new SAX Parser instance
        SAXParser saxParser = factory.newSAXParser();
        // We need the SAX parser handler object
        SettingsParseHandler handler = new SettingsParseHandler();
        // We call the method parsing our RSS Feed
        if(input!=null)
        	saxParser.parse(input, handler);
        else
        	saxParser.parse(configUrl, handler);
        
        // The result of the parsing process is being stored in the handler object
        return handler.getItems();
    }
}
