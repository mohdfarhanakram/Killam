package com.killam.apartment.model.list;

import com.google.gson.annotations.SerializedName;


public class Building {
	
	private String id;

	private String buildingCode;
	
	private String propertyName;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String postalCode;
	
	private City city;
	
	private Province province;
	
	private double lat;
	
	@SerializedName("long")
	private double lon;
	
	private Photo photo;
	
	//public String units;
	
	private double distance;
	

    /**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getId() {
        return id;
    }

    public String getbuildingCode() {
        return buildingCode;
    }
    
    public String getpropertyName() {
        return propertyName;
    }
    
    public String getaddressLine1() {
        return addressLine1;
    }

    public String getaddressLine2() {
        return addressLine2;
    }
    
    public String getpostalCode() {
        return postalCode;
    }
    
    /*public String getunits() {
        return units;
    }
    */
    public double getlat() {
        return lat;
    }
    
    public double getlon() {
        return lon;
    }
    
    public Photo getphoto() {
        return photo;
    }

    public Province getprovince() {
        return province;
    }

    public City getcity() {
        return city;
    }
    
    
}
