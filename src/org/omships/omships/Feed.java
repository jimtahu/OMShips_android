package org.omships.omships;

public class Feed {
	private String url;
	private String type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}//end Feed
