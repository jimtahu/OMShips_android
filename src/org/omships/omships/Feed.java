package org.omships.omships;

public class Feed {
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
	public Feed(){}
	public Feed(String url){
		this.url=url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
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
	
	public Reader<FeedItem> getReader(){
		switch(this.getType()){
		case rss:
			return new RSSReader(this.getUrl());
		case twitter:
			return new TwitterReader(this.getUrl());
		case vimeo:
			return new VimeoReader(this.getUrl());
		case port:
			return new PortReader(this.getUrl());
		default:
			return new RSSReader(this.getUrl());
		}
	}//end getReader
	
	@Override
	public String toString() {
		return this.getType()+"://"+getUrl();
	}
}//end Feed
