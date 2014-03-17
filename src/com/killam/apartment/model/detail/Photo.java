/**
 * 
 */
package com.killam.apartment.model.detail;

/**
 * @author m.farhan
 *
 */
public class Photo {

	/**
	 * 
	 */
	private String originalFileName;
	private String thumbnailUrl;
	private String fullUrl;
	
	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public Photo() {
		// TODO Auto-generated constructor stub
	}

}
