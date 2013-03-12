package org.omships.omships;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple case for an item in an "RSS" feed.
 * @author jimtahu
 * Based on http://www.itcuties.com/android/how-to-write-android-rss-parser/
 */
public class RSSItem implements Parcelable{
	private String title;
	private String link;
	private Date pubDate;
	private String description;
	
	public RSSItem(){
		this.title = "None";
		this.link = "http://www.google.com";
		this.pubDate = new Date();
		this.description = "No news is good news";
	}

	public RSSItem(Parcel in){
		this.title=in.readString();
		this.link=in.readString();
		this.pubDate=new Date(in.readString());
		this.description=in.readString();
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		return this.title;
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
		dest.writeString(getPubDate().toString());
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

}//end class RSSItem
