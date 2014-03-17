/**
 * 
 */
package com.killam.apartment.model.detail;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

/**
 * @author m.farhan
 *
 */
public class Building {

	/**
	 * 
	 */
	private String id;
	private String description;
	private String contactName;
	private String contactEmail;
	private String contactPhone;
	private String leasingPhone;
	private String leasingEmail;
	private String superName;
	private String superEmail;
	private String superPhone;
	private String propertyName;
	private String addressLine1;
	private String addressLine2;
	private City city;
	private Province province;
	private Object postalCode;
	private Object buildingType;
	private String lat;
	@SerializedName("long")
	private String lon;
	private String showSurrogateMomBanner;
	private String metaDescription;
	private ArrayList<Photo> photos;
	private ArrayList<Feature> features;
	private ArrayList<Unit> units;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		if(description==null)
			description = "";
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getLeasingPhone() {
		return leasingPhone;
	}

	public void setLeasingPhone(String leasingPhone) {
		this.leasingPhone = leasingPhone;
	}

	public String getLeasingEmail() {
		return leasingEmail;
	}

	public void setLeasingEmail(String leasingEmail) {
		this.leasingEmail = leasingEmail;
	}

	public String getSuperName() {
		return superName;
	}

	public void setSuperName(String superName) {
		this.superName = superName;
	}

	public String getSuperEmail() {
		return superEmail;
	}

	public void setSuperEmail(String superEmail) {
		this.superEmail = superEmail;
	}

	public String getSuperPhone() {
		return superPhone;
	}

	public void setSuperPhone(String superPhone) {
		this.superPhone = superPhone;
	}

	public String getPropertyName() {
		if(propertyName==null)
			return "";
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getAddressLine1() {
		if(addressLine1==null)
			addressLine1="";
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		if(addressLine2==null)
			addressLine2="";
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Object getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Object postalCode) {
		this.postalCode = postalCode;
	}

	public Object getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(Object buildingType) {
		this.buildingType = buildingType;
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

	public String getShowSurrogateMomBanner() {
		return showSurrogateMomBanner;
	}

	public void setShowSurrogateMomBanner(String showSurrogateMomBanner) {
		this.showSurrogateMomBanner = showSurrogateMomBanner;
	}

	public String getMetaDescription() {
		if(metaDescription==null)
			return "";
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public ArrayList<Photo> getPhotos() {
		if(photos==null)
			photos = new ArrayList<Photo>();
		return photos;
	}

	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}

	public ArrayList<Feature> getFeatures() {
		if(features==null)
		     features = new ArrayList<Feature>();
		return features;
	}

	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}

	public ArrayList<Unit> getUnits() {
		if(units==null)
			units = new ArrayList<Unit>();
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public Building() {
		// TODO Auto-generated constructor stub
	}

}
