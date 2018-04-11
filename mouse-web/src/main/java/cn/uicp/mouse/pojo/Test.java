package cn.uicp.mouse.pojo;

import java.io.Serializable;

public class Test implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nm_sid;
	
	private String st_name;
	
	private String st_loginname;
	
	private String st_pass;
	
	private String nm_state;
	
	private String dt_update;
	
	private String dt_lastlogindate;
	
	private String nm_failcount;

	public String getNm_sid() {
		return nm_sid;
	}

	public void setNm_sid(String nm_sid) {
		this.nm_sid = nm_sid;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_loginname() {
		return st_loginname;
	}

	public void setSt_loginname(String st_loginname) {
		this.st_loginname = st_loginname;
	}

	public String getSt_pass() {
		return st_pass;
	}

	public void setSt_pass(String st_pass) {
		this.st_pass = st_pass;
	}

	public String getNm_state() {
		return nm_state;
	}

	public void setNm_state(String nm_state) {
		this.nm_state = nm_state;
	}

	public String getDt_update() {
		return dt_update;
	}

	public void setDt_update(String dt_update) {
		this.dt_update = dt_update;
	}

	public String getDt_lastlogindate() {
		return dt_lastlogindate;
	}

	public void setDt_lastlogindate(String dt_lastlogindate) {
		this.dt_lastlogindate = dt_lastlogindate;
	}

	public String getNm_failcount() {
		return nm_failcount;
	}

	public void setNm_failcount(String nm_failcount) {
		this.nm_failcount = nm_failcount;
	}
	
	
	
	
}
