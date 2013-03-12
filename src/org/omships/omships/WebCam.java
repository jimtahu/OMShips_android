package org.omships.omships;

import android.os.Parcel;
import android.os.Parcelable;

public class WebCam implements Parcelable{
	String url;
	String name;
	long update;
	public WebCam(){};
	
	public WebCam(Parcel source) {
		this.name=source.readString();
		this.url=source.readString();
		this.update=source.readLong();
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUpdate() {
		return update;
	}
	public void setUpdate(long update) {
		this.update = update;
	}
	public String toString(){
		return this.name;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.getName());
		dest.writeString(this.getUrl());
		dest.writeLong(this.getUpdate());
	}
	
	public static final Parcelable.Creator<WebCam> CREATOR = new Parcelable.Creator<WebCam>() {
		@Override
		public WebCam createFromParcel(Parcel source) {
			return new WebCam(source);
		}
		@Override
		public WebCam[] newArray(int size) {
			return new WebCam[size];
		}
	};
	
}//end class WebCam
