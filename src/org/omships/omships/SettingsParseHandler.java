package org.omships.omships;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX tag handler.
 * @author jimtahu
 * from http://www.itcuties.com/android/how-to-write-android-rss-parser/
 */
public class SettingsParseHandler extends DefaultHandler {
	private enum SubItem {
		none,
		ship,
		webcams,
		news,
		media,
	}
	
    // List of ships parsed
    private List<Ship> ships;
    private List<WebCam> currentWebCams;
    private List<Feed> currentNews;
    private List<Feed> currentMedia;
    private Ship currentShip;
    private WebCam currentWebCam;
    
    private SubItem currentSub;
 
    public SettingsParseHandler() {
        ships = new ArrayList<Ship>();
        currentSub = SubItem.none;
    }
    
    /**
     * 
     * @return list of Ships in the config file
     */
    public List<Ship> getItems() {
        return ships;
    }
    
    Feed buildFeed(Attributes attributes){
    	Feed feed = new Feed();
    	feed.setType(attributes.getValue("type"));
    	switch(feed.getType()){
    	case rss:
    		feed.setUrl(attributes.getValue("url"));
    		break;
    	case twitter:
    		feed.setUrl(attributes.getValue("handle"));
    		break;
    	case vimeo:
    		feed.setUrl(attributes.getValue("person"));
    		break;
		default:
			break;
    	}
    	return feed;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("ship".equals(qName)) {
            currentShip = new Ship();
            currentShip.setName(attributes.getValue("name"));
            currentSub = SubItem.ship;
        } else if("location".contains(qName) && currentShip!=null){
        	currentShip.setLocation(attributes.getValue("url"));
    	} else if("ports".contains(qName)){
    		currentShip.setPorts((attributes.getValue("url")));
    	} else if ("news".equals(qName) && currentSub==SubItem.ship) {
    		currentSub = SubItem.news;
    		currentNews = new ArrayList<Feed>();
        } else if ("media".equals(qName) && currentSub==SubItem.ship) {
            currentSub = SubItem.media;
            currentMedia = new ArrayList<Feed>();
        } else if ("webcams".equals(qName) && currentSub==SubItem.ship){
        	currentSub = SubItem.webcams;
        	currentWebCams = new ArrayList<WebCam>();
        } else if ("webcam".equals(qName) && currentSub==SubItem.webcams){
        	currentWebCam = new WebCam();
        	currentWebCam.setName(attributes.getValue("name"));
        	currentWebCam.setUrl(attributes.getValue("url"));
        	currentWebCam.setUpdate(Long.parseLong(attributes.getValue("updateInterval")));
        	currentWebCams.add(currentWebCam);
        } else if ("feed".equals(qName)) {
        	Feed feed=buildFeed(attributes);
        	if(currentSub==SubItem.news && currentNews!=null){
        		currentNews.add(feed);
        	}else if(currentSub==SubItem.media && currentMedia!=null){
        		currentMedia.add(feed);
        	}
        }
    }//end startElement 
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("ship".equals(qName)) {
            ships.add(currentShip);
            currentSub=SubItem.none;
            currentShip=null;
        } else if("webcams".equals(qName) && currentShip!=null){
        	currentShip.setWebcams(currentWebCams);
        	currentWebCams=null;
        	currentSub=SubItem.ship;
        } else if("news".equals(qName) && currentShip!=null){
        	currentShip.setNews(currentNews);
        	currentNews=null;
        	currentSub=SubItem.ship;
        } else if("media".equals(qName) && currentShip!=null){
        	currentShip.setMedia(currentMedia);
        	currentMedia=null;
        	currentSub=SubItem.ship;
        }
    }//end endElement
}//end class
