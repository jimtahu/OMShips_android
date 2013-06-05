/**
 * 
 */
package org.omships.omships.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import org.omships.omships.FeedItem;

import android.os.Parcel;
import android.test.AndroidTestCase;

/**
 * @author jimtahu
 *
 */
public class FeedItemTest extends AndroidTestCase {

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see android.test.AndroidTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#writeToParcel()}.
	 */
	public void testWriteToParcel(){
		FeedItem planet = new FeedItem();
		planet.setTitle("Google");
		planet.setLink("http://www.google.com");
		planet.setDescription("a web page");
		planet.setPubDate(new Date());
		Parcel tmp = Parcel.obtain();
		planet.writeToParcel(tmp, 0);
		tmp.setDataPosition(0);
		FeedItem ship = FeedItem.CREATOR.createFromParcel(tmp);
		assertEquals(planet.getTitle(),ship.getTitle());
		assertEquals(planet.getDescription(),ship.getDescription());
		assertEquals(planet.getLink(),ship.getLink());
		assertEquals(planet.getPubDate().toString(),ship.getPubDate().toString());
		tmp.recycle();
	}
	
	/**
	 * Test method for {@link org.omships.omships.FeedItem#isImage()}.
	 */
	public void testIsImage() {
		FeedItem item = new FeedItem();
		item.setLink("http://www.google.com");
		assertEquals(false, item.isImage());
		item.setLink("http://somecoolwebsite/photo.jpg");
		assertEquals(true, item.isImage());
		item.setLink("http://somecoolwebsite/photo.jpeg");
		assertEquals(true, item.isImage());
		item.setLink("http://somecoolwebsite/photo.png");
		assertEquals(true, item.isImage());
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#isVideo()}.
	 */
	public void testIsVideo() {
		FeedItem item = new FeedItem();
		item.setLink("http://www.google.com");
		assertEquals(false, item.isVideo());
		item.setLink("http://vimeo.com/omships/videos/rss");
		assertEquals(true, item.isVideo());
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#isWebPage()}.
	 */
	public void testIsWebPage() {
		FeedItem item = new FeedItem();
		item.setLink("http://www.google.com");
		assertEquals(true, item.isWebPage());
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#getTitle()}.
	 */
	public void testGetTitle() {
		String titles[] = {
				"Test Feed Title","A differant Title",
				"'TItle' with #funny char' in it", "12345" };
		FeedItem item = new FeedItem();
		for(int i=0;i<titles.length;i++){
			item.setTitle(titles[i]);
			assertEquals(titles[i], item.getTitle());
		}
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#getLink()}.
	 */
	public void testGetLink() {
		String links[] = {"http://www.google.com","https://www.google.com"};
		FeedItem item = new FeedItem();
		for(int i=0;i<links.length;i++){
			item.setLink(links[i]);
			assertEquals(links[i], item.getLink());
		}
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#getPubDate()}.
	 */
	public void testGetPubDate() {
		FeedItem item = new FeedItem();
		Date now = new Date();
		item.setPubDate(now);
		assertEquals(now.toString(),item.getPubDate().toString());
		item = new FeedItem();
		item.setPubDate(FeedItem.format.format(now));
		assertEquals(now.toString(),item.getPubDate().toString());
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#getDescription()}.
	 */
	public void testGetDescription() {
		String words[] = {
				"Test Feed Title","A differant Title",
				"'TItle' with #funny char' in it", "12345" };
		FeedItem item = new FeedItem();
		for(int i=0;i<words.length;i++){
			item.setDescription(words[i]);
			assertEquals(words[i], item.getDescription());
		}
	}

	/**
	 * Test method for {@link org.omships.omships.FeedItem#toString()}.
	 */
	public void testToString() {
		FeedItem item = new FeedItem();
		item.setTitle("Google");
		item.setLink("http://www.google.com");
		item.setDescription("a web page");
		assertEquals("a web page",item.toString());
		item.setLink("http://www.google.com/photo.png");
		assertEquals("PHOTO: "+"a web page",item.toString());
	}
	
	/**
	 * Test method for {@link org.omships.omships.FeedItem#compareTo(org.omships.omships.FeedItem)}.
	 * @throws ParseException 
	 */
	public void testCompareTo() throws ParseException {
		SimpleDateFormat format =
				new SimpleDateFormat("dd MMM yyyy",Locale.US);
		ArrayList<FeedItem> list= new ArrayList<FeedItem>();
		FeedItem item= new FeedItem();
		item.setTitle("D");
		item.setPubDate(format.parse("07 May 2010"));
		list.add(item);
		item= new FeedItem();
		item.setTitle("B");
		item.setPubDate(format.parse("09 May 2010"));
		list.add(item);
		item= new FeedItem();
		item.setTitle("C");
		item.setPubDate(format.parse("08 May 2010"));
		list.add(item);
		item= new FeedItem();
		item.setTitle("A");
		item.setPubDate(format.parse("10 May 2010"));
		list.add(item);
		Collections.sort(list);
		assertEquals("A",list.get(0).getTitle());
		assertEquals("B",list.get(1).getTitle());
		assertEquals("C",list.get(2).getTitle());
		assertEquals("D",list.get(3).getTitle());
	}

}
