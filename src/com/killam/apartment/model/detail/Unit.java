/**
 * 
 */
package com.killam.apartment.model.detail;

/**
 * @author m.farhan
 *
 */
public class Unit {

	/**
	 * 
	 */
	private String bedrooms;
	private String rent;
	private String availabilityLabel;
	private String availableNow;
	private String applicationFormUrl;
	
	public String getBedrooms() {
		if(bedrooms==null)
			return "";
		return bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getAvailabilityLabel() {
		if(availabilityLabel==null)
			return "";
		return availabilityLabel;
	}

	public void setAvailabilityLabel(String availabilityLabel) {
		this.availabilityLabel = availabilityLabel;
	}

	public String getAvailableNow() {
		return availableNow;
	}

	public void setAvailableNow(String availableNow) {
		this.availableNow = availableNow;
	}

	public String getApplicationFormUrl() {
		return applicationFormUrl;
	}

	public void setApplicationFormUrl(String applicationFormUrl) {
		this.applicationFormUrl = applicationFormUrl;
	}

	public Unit() {
		// TODO Auto-generated constructor stub
	}

}
