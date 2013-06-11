package org.omships.omships.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds configurations for a ship.
 * @author jimtahu
 */
public class Ship {
	private String name;
	private String location;
	private Feed ports;
	private List<Feed> news;
	private List<Feed> media;
	private List<WebCam> webcams;
	
	public Ship() {
		this.name="None";
		this.news = new ArrayList<Feed>();
		this.media = new ArrayList<Feed>();
		this.webcams = new ArrayList<WebCam>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Feed getPorts() {
		return ports;
	}
	public void setPorts(Feed ports) {
		this.ports = ports;
	}
	public List<Feed> getNews() {
		return news;
	}
	public void setNews(List<Feed> news) {
		this.news = news;
	}
	public void addNews(Feed news){
		this.news.add(news);
	}
	public List<Feed> getMedia() {
		return media;
	}
	public void setMedia(List<Feed> media) {
		this.media = media;
	}
	public void addMedia(Feed media){
		this.media.add(media);
	}
	public List<WebCam> getWebcams() {
		return webcams;
	}
	public void setWebcams(List<WebCam> webcams) {
		this.webcams = webcams;
	}
	public void addWebcam(WebCam cam){
		this.webcams.add(cam);
	}
	
	public String toString(){
		return this.name;
	}
}//end class ShipConfig
