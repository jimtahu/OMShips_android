package org.omships.omships.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.omships.omships.FeedItem;
import org.omships.omships.RssParseHandler;
import org.xml.sax.SAXException;

import android.test.AndroidTestCase;

public class RSSParseHandlerTest extends AndroidTestCase {

	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?> "
			+"<rss version=\"2.0\" xmlns:om=\"http://www.omships.org/dtd/rss.dtd\" xmlns:atom=\"http://www.w3.org/2005/Atom\">"
			+"<channel>"
			+"<title><![CDATA[Fake RSS feed]]></title>"
			+"<link>http://link.org</link>"
			+"<description><![CDATA[A fake feed top test our parser]]></description>"
			+"<atom:link href=\"http://link.org\" rel=\"self\" type=\"application/rss+xml\" />"
			+"<language>en-uk</language>";
	public static final String XML_FOOT="</channel></rss>";
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public String xml_item(String title,String link,String date, String desc){
		StringBuffer buff = new StringBuffer();
		buff.append("<item>");
		buff.append("<title><![CDATA[").append(title).append("]]></title>");
		buff.append("<link><![CDATA[").append(link).append("]]></link>");
		buff.append("<pubDate><![CDATA[").append(date).append("]]></pubDate>");
		buff.append("<description><![CDATA[").append(desc).append("]]></description>");
		buff.append("<guid isPermaLink=\"true\"><![CDATA[");
		buff.append(buff.hashCode()).append("]]></guid>");
		buff.append("</item>");
		return buff.toString();
	}
	
	public void testGetItems() {
		fail("Not yet implemented");
	}
	
	public void testSmoke() throws ParserConfigurationException, SAXException, IOException {
		/*
		String test = XML_HEAD
				+"<item>"
+"<title><![CDATA[Test Item]]></title>"
+"<link><![CDATA[http://test.org]]></link>"
+"<guid isPermaLink=\"true\"><![CDATA[R35879]]></guid>"
+"<pubDate><![CDATA[Sun, 31 May 2013 20:15:07 +0000]]></pubDate>"
+"<description><![CDATA[A smoke test of the parser]]></description>"
+"</item>"
				+XML_FOOT;
				*/
		String test = XML_HEAD
				+ xml_item("Test Item","http://test.org",
						"Sun, 31 May 2013 20:15:07 +0000",
						"A smoke test of the parser")
				+ XML_FOOT;
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RssParseHandler handler = new RssParseHandler();     
        saxParser.parse(new ByteArrayInputStream(test.getBytes()), handler);
        List<FeedItem> list = handler.getItems();
        assertEquals(1, list.size());
	}
}
