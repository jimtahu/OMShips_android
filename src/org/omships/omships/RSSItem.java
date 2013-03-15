package org.omships.omships;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple case for an item in an "RSS" feed.
 * @author jimtahu
 * Based on http://www.itcuties.com/android/how-to-write-android-rss-parser/
 */
public class RSSItem implements Parcelable,Comparable<RSSItem>{
	private String title;
	private String link;
	private Date pubDate;
	private String description;
	
	final static SimpleDateFormat format =
			new SimpleDateFormat("EEE, dd MMM yyyy k:m:s",Locale.US);
	
	public RSSItem(){
		this.title = "None";
		this.link = "http://www.google.com";
		this.pubDate = new Date();
		this.description = "No news is good news";
	}

	public RSSItem(Parcel in){
		this.title=in.readString();
		this.link=in.readString();
		try {
			this.pubDate=format.parse(in.readString());
		} catch (ParseException e) {
			this.pubDate = new Date();
			e.printStackTrace();
		}
		this.description=in.readString();
	}
	
	/**
	 * Checks if the linked item appears to be an image.
	 * @return
	 */
	public boolean isImage(){
		if(this.getLink().endsWith(".jpg"))return true;
		else if(this.getLink().endsWith(".jpeg"))return true;
		else if(this.getLink().endsWith(".png"))return true;
		else return false;
	}
	
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
	public void setPubDate(String date){
		try {
			this.pubDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		return this.description;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(getTitle());
		dest.writeString(getLink());
		dest.writeString(format.format(getPubDate()));
		dest.writeString(getDescription());
	}
	
	public static final Parcelable.Creator<RSSItem> CREATOR = new Parcelable.Creator<RSSItem>() {
		@Override
		public RSSItem createFromParcel(Parcel source) {
			return new RSSItem(source);
		}
		@Override
		public RSSItem[] newArray(int size) {
			return new RSSItem[size];
		}
	};

	@Override
	public int compareTo(RSSItem arg0) {
		if(this.getPubDate().compareTo(arg0.getPubDate())!=0)
			return this.getPubDate().compareTo(arg0.getPubDate());
		else 
			return this.getTitle().compareTo(arg0.getTitle());
	}

}//end class RSSItem
