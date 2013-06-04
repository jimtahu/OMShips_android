package org.omships.omships.test;

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
		fail("Not yet implemented");
	}

}
