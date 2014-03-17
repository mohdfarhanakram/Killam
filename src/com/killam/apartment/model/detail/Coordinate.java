/**
 * 
 */
package com.killam.apartment.model.detail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author TP150
 *
 */
public class Coordinate implements Parcelable{

	/**
	 * 
	 */
	private String buildingId="";
	private String lat = "0.0";
	private String lon = "0.0";
	private String markerTitle = "";
	private String addressLine = "";
	
	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	
	
	
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	

	public String getMarkerTitle() {
		return markerTitle;
	}

	public void setMarkerTitle(String markerTitle) {
		this.markerTitle = markerTitle;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}


	public Coordinate(String buildingId,String markerTitle,String lat,String lon,String addressLine) {
		// TODO Auto-generated constructor stub
		this.buildingId  = buildingId;
		this.lat         = lat;
		this.lon         = lon;
		this.markerTitle = markerTitle;
		this.addressLine = addressLine;
		
	}
	
	public Coordinate(Parcel in) {
		// TODO Auto-generated constructor stub
		buildingId  = in.readString();
		lat         = in.readString();
		lon         = in.readString();
		markerTitle = in.readString();
		addressLine = in.readString();
		
		
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(buildingId);
		dest.writeString(lat);
		dest.writeString(lon);
		dest.writeString(markerTitle);
		dest.writeString(addressLine);
		
		
	}
	
	// this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
	public static final Parcelable.Creator<Coordinate> CREATOR = new Parcelable.Creator<Coordinate>() {
	    public Coordinate createFromParcel(Parcel in) {
	        return new Coordinate(in);
	    }

	    public Coordinate[] newArray(int size) {
	        return new Coordinate[size];
	    }
	};

}
