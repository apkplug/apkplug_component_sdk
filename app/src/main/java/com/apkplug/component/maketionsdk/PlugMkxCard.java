package com.apkplug.component.maketionsdk;

public class PlugMkxCard {
	@Override
	public String toString() {
		return "MkxCardInPlug [address=" + address + ", audit=" + audit
				+ ", carduuid=" + carduuid + ", cname=" + cname + ", creatime="
				+ creatime + ", duty=" + duty + ", email=" + email + ", fax="
				+ fax + ", fields=" + fields + ", flag=" + flag + ", logo="
				+ logo + ", mobile1=" + mobile1 + ", mobile2=" + mobile2
				+ ", name=" + name + ", tell1=" + tell1 + ", tell2=" + tell2
				+ ", updatetime=" + updatetime + ", website=" + website + "]";
	}
	private String address;
	private int  audit;
	private String carduuid;
	private String cname;
	private long creatime;
	private String duty;
	private String email;
	private String fax;
	private String fields;
	private int flag;
	private String logo;
	private String mobile1;
	private String mobile2;
	private String name;
	
	private String tell1;
	private String tell2;
	private long updatetime;
	private String website;
	
	public PlugMkxCard() {
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAudit() {
		return audit;
	}
	public void setAudit(int audit) {
		this.audit = audit;
	}
	public String getCarduuid() {
		return carduuid;
	}
	public void setCarduuid(String carduuid) {
		this.carduuid = carduuid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public long getCreatime() {
		return creatime;
	}
	public void setCreatime(long creatime) {
		this.creatime = creatime;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTell1() {
		return tell1;
	}
	public void setTell1(String tell1) {
		this.tell1 = tell1;
	}
	public String getTell2() {
		return tell2;
	}
	public void setTell2(String tell2) {
		this.tell2 = tell2;
	}
	public long getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}
