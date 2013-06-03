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
	
	public List<FeedItem> xml_parse(String test) throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        RssParseHandler handler = new RssParseHandler();     
        saxParser.parse(new ByteArrayInputStream(test.getBytes()), handler);
        return handler.getItems();
	}
	
	public void testSmoke() throws ParserConfigurationException, SAXException, IOException {
		String test = XML_HEAD
				+ xml_item("Test Item","http://test.org",
						"Sun, 31 May 2013 20:15:07 +0000",
						"A smoke test of the parser")
				+ XML_FOOT;
        assertEquals(1, xml_parse(test).size());
	}
	
	public void testStringMarks() throws ParserConfigurationException, SAXException, IOException {
		String texts[] = {
				"A 'postrophe eludes us",
				"He said \"Quiphobes\" my good sir.",
				"Kneel before \'Zod\'!",
				"The \' and \" do not match."
		};
		String test = XML_HEAD
				+ xml_item("Apostrophe","http://test.org",
						"Sun, 31 May 2013 20:15:07 +0000",
						texts[0])
				+ xml_item("String String","http://test.org",
						"Sun, 30 May 2013 20:15:07 +0000",
						texts[1])
				+ xml_item("Single String","http://test.org",
						"Sun, 29 May 2013 20:15:07 +0000",
						texts[2])
				+ xml_item("Miss-Quotes","http://test.org",
						"Sun, 28 May 2013 20:15:07 +0000",
						texts[3])
				+ XML_FOOT;
        List<FeedItem> list = xml_parse(test);
        assertEquals(texts.length, list.size());
        for(int i=0;i<texts.length;i++)
        	assertEquals(texts[i],list.get(i).getDescription());
	}//end testStringMarks
}