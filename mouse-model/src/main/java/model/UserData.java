package model;

import java.io.Serializable;

public class UserData implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private String tel;
	
	private String companyname;
	
	private String address;
	
	private String keyword;
	
	private String companytype;
	
	private String email;
	
	private Integer telType;
	
	private String datetime;
	
	private String urlsource;
	
	private String city;
	
	private String Management;
	
	private String comUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCompanytype() {
		return companytype;
	}

	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelType() {
		return telType;
	}

	public void setTelType(Integer telType) {
		this.telType = telType;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getUrlsource() {
		return urlsource;
	}

	public void setUrlsource(String urlsource) {
		this.urlsource = urlsource;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getManagement() {
		return Management;
	}

	public void setManagement(String management) {
		Management = management;
	}

	public String getComUrl() {
		return comUrl;
	}

	public void setComUrl(String comUrl) {
		this.comUrl = comUrl;
	}
	
}
