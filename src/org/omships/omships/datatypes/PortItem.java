package org.omships.omships.datatypes;

import org.omships.omships.R;

/**
 * Describes a port stop. 
 */
public class PortItem extends FeedItem {
	private String city;
	private String country;
	private boolean confirmed;
	private String arrive;
	private String depart;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = (confirmed.equals("yes") || confirmed.equals("true"));
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}

	/**
	 * Natural sorting of port stops.
	 * @param arg0
	 * @return
	 * Based on arrival date.
	 */
	public int compareTo(PortItem arg0) {
		return this.getArrive().compareTo(arg0.getArrive());
	}
	@Override
	public int getImageResource() {
		return R.drawable.ship;
	}
	@Override
	public String toString() {
		StringBuilder build=new StringBuilder();
		build.append(getCity());
		build.append(", ");
		build.append(getCountry());
		build.append(" \n ");
		build.append(getArrive());
		build.append(" to ");
		build.append(getDepart());
		return build.toString();
	}
}//end PortItem
