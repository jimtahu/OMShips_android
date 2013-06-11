package org.omships.omships.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import org.omships.omships.FeedItem;
import org.omships.omships.PortItem;

import android.test.AndroidTestCase;

public class PortItemTest extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testToString() {
		PortItem port = new PortItem();
		port.setCountry("Juna");
		port.setCity("P3X-729");
		port.setArrive("Thu, 28 May 2013");
		port.setDepart("Sun, 31 May 2013");
		StringBuffer buffer=new StringBuffer();
		buffer.append("Juna");
		buffer.append(", ");
		buffer.append("P3X-729");
		buffer.append(" \n ");
		buffer.append("Thu, 28 May 2013");
		buffer.append(" to ");
		buffer.append("Sun, 31 May 2013");
	}

	public void testGetCity() {
		String places[] = {"P2X-555",
				"P2X-787",
				"P2X-885",
				"P2X-887",
				"P3A-194",
				"P3C-117",
				"P3C-249",};
		PortItem port = new PortItem();
		for(int i=0;i<places.length;i++){
			port.setCity(places[i]);
			assertEquals(places[i],port.getCity());
		}
	}

	public void testGetCountry() {
		String places[] = {"SG-1","Chulak","Dakara","Abydos","Tollana"};
		PortItem port = new PortItem();
		for(int i=0;i<places.length;i++){
			port.setCountry(places[i]);
			assertEquals(places[i],port.getCountry());
		}
	}

	public void testIsConfirmed() {
		PortItem port = new PortItem();
		port.setConfirmed(false);
		assertEquals(false, port.isConfirmed());
		port.setConfirmed(true);
		assertEquals(true, port.isConfirmed());
		port.setConfirmed("no");
		assertEquals(false, port.isConfirmed());
		port.setConfirmed("false");
		assertEquals(false, port.isConfirmed());
		port.setConfirmed("yes");
		assertEquals(true, port.isConfirmed());
		port.setConfirmed("true");
		assertEquals(true, port.isConfirmed());
	}

	public void testGetArrive() {
		String dates[] = {"Sun, 31 May 2013 20:15:07 +0000",
				"Sat, 30 May 2013 20:15:07 +0000",
				"Fri, 29 May 2013 20:15:07 +0000",
				"Thu, 28 May 2013 20:15:07 +0000"};
		PortItem port = new PortItem();
		for(int i=0;i<dates.length;i++){
			port.setArrive(dates[i]);
			assertEquals(dates[i],port.getArrive());
		}
	}

	public void testGetDepart() {
		String dates[] = {"Sun, 31 May 2013 20:15:07 +0000",
				"Sat, 30 May 2013 20:15:07 +0000",
				"Fri, 29 May 2013 20:15:07 +0000",
				"Thu, 28 May 2013 20:15:07 +0000"};
		PortItem port = new PortItem();
		for(int i=0;i<dates.length;i++){
			port.setDepart(dates[i]);
			assertEquals(dates[i],port.getDepart());
		}
	}

	public void testCompareToPortItem() {
		SimpleDateFormat format =
				new SimpleDateFormat("dd MMM yyyy",Locale.US);
		ArrayList<PortItem> list = new ArrayList<PortItem>();
		PortItem item = new PortItem();
		item.setCity("D");
		item.setArrive("07 May 2010");
		list.add(item);
		item= new PortItem();
		item.setCity("B");
		item.setArrive("09 May 2010");
		list.add(item);
		item= new PortItem();
		item.setCity("C");
		item.setArrive("08 May 2010");
		list.add(item);
		item= new PortItem();
		item.setCity("A");
		item.setArrive("10 May 2010");
		list.add(item);
		Collections.sort(list);
		assertEquals("A",list.get(0).getCity());
		assertEquals("B",list.get(1).getCity());
		assertEquals("C",list.get(2).getCity());
		assertEquals("D",list.get(3).getCity());
	}

}
