package org.omships.omships;

import java.util.Date;

/**
 * Simple case for an item in an "RSS" feed.
 * @author jimtahu
 * Based on http://www.itcuties.com/android/how-to-write-android-rss-parser/
 */
public class RSSItem {
	private String title;
	private String link;
	private Date pubDate;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		return this.title;
	}
	
}//end class RSSItem
