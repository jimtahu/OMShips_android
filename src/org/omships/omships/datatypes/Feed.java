package org.omships.omships.datatypes;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import org.omships.omships.FileBank;
import org.omships.omships.parse.PortReader;
import org.omships.omships.parse.RSSReader;
import org.omships.omships.parse.Reader;
import org.omships.omships.parse.TwitterReader;
import org.omships.omships.parse.VimeoReader;

/**
 * Feed source. 
 * @author jimtahu
 * This class describes a feed (which can be parsed for later use).
 */
public class Feed {
	/**
	 * Used to indicate which type of Feed this is.
	 * We use this rather than subclassing feed for a reason
	 *  which I currently do not remember. 
	 */
	public static enum Type{
		rss,
		photo,
		twitter,
		vimeo,
		port,
		other,
	}
	private String url;
	private Type type;
	private int max;
	
	public Feed(){max=3;}
	/** Create a feed pointing to the given url */
	public Feed(String url){
		this.url=url;
		this.max=3;
	}
	public int getMax() {
		return max;
	}
	/**
	 * Sets the maximum items the feed should return.
	 * @param count
	 * Defaults to 3.
	 */
	public void setMax(int count) {
		this.max=count;
	}
	public InputStream getStream() throws MalformedURLException, IOException{
		if(this.getType().equals(Type.twitter)){
			return FileBank.openStream(
					"https://api.twitter.com/1/statuses/user_timeline.rss?screen_name="
					+getUrl());
		}else if(this.getType().equals(Type.vimeo)){
			return FileBank.openStream("http://vimeo.com/"+getUrl()+"/videos/rss");
		}else{
			return FileBank.openStream(getUrl());
		}
	}
	public String getUrl() {
		return url;
	}
	/**
	 * Sets the url for this feed.
	 * @param url
	 * Not necessarily a real url, for instance:
	 * Twitter type is just the username. 
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	public Type getType() {
		return type;
	}
	/**
	 * Sets the type of the feed.
	 * @param type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * Sets the type of the feed.
	 * @param value
	 * If the string is not a valid feed type, it will be set to 'other'.
	 */
	public void setType(String value) {
		if(value==null)return;
		if(value.equals("rss"))
			this.type=Type.rss;
		else if(value.equals("photo"))
			this.type=Type.photo;
		else if(value.equals("twitter"))
			this.type=Type.twitter;
		else if(value.equals("vimeo"))
			this.type=Type.vimeo;
		else if(value.equals("port"))
			this.type=Type.port;
		else this.type=Type.other;
	}
	
	/**
	 * Create a feed reader for this feed. 
	 * @return An appropriate reader for this feed.
	 */
	public Reader<FeedItem> getReader(){
		switch(this.getType()){
		case rss:
			return new RSSReader(this);
		case twitter:
			return new TwitterReader(this);
		case vimeo:
			return new VimeoReader(this);
		case port:
			return new PortReader(this);
		default:
			return new RSSReader(this);
		}
	}//end getReader
	
	@Override
	public String toString() {
		return this.getType()+"://"+getUrl();
	}
}//end Feed
