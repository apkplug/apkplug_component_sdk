package com.apkplug.component.sharesdk;

import java.io.Serializable;

public class PlugShareInfo implements Serializable{
	private String address=null;
	private String title=null;
	private String titleUrl=null;
	private String text=null;
	private String imagePath=null;
	private String imageUrl=null;
	private String url=null;
	private String filePath=null;
	private String comment=null;
	private String site=null;
	private String siteUrl=null;
	private String venueName=null;
	private String venueDescription=null;
	private float latitude=0;
	private float longitude=0;
	private boolean disableSSOWhenAuthorize=false;
	/**
	 * @return 是否关闭sso授权
	 */
	public boolean isDisableSSOWhenAuthorize() {
		return disableSSOWhenAuthorize;
	}
	/**
	 * @param 是否关闭sso授权
	 */
	public void setDisableSSOWhenAuthorize(boolean disableSSOWhenAuthorize) {
		this.disableSSOWhenAuthorize = disableSSOWhenAuthorize;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address 要设置的 address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title 要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return titleUrl
	 */
	public String getTitleUrl() {
		return titleUrl;
	}
	/**
	 * @param titleUrl 要设置的 titleUrl
	 */
	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}
	/**
	 * @return text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text 要设置的 text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath 要设置的 imagePath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * @return imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl 要设置的 imageUrl
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url 要设置的 url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath 要设置的 filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment 要设置的 comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return site
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site 要设置的 site
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * @return siteUrl
	 */
	public String getSiteUrl() {
		return siteUrl;
	}
	/**
	 * @param siteUrl 要设置的 siteUrl
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	/**
	 * @return venueName
	 */
	public String getVenueName() {
		return venueName;
	}
	/**
	 * @param venueName 要设置的 venueName
	 */
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	/**
	 * @return venueDescription
	 */
	public String getVenueDescription() {
		return venueDescription;
	}
	/**
	 * @param venueDescription 要设置的 venueDescription
	 */
	public void setVenueDescription(String venueDescription) {
		this.venueDescription = venueDescription;
	}
	/**
	 * @return latitude
	 */
	public float getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude 要设置的 latitude
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return longitude
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude 要设置的 longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
