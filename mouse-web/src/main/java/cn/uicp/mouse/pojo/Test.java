package cn.uicp.mouse.pojo;

import java.io.Serializable;

public class Test implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uname;
	
	private String upaw;
	
	private String sex;
	
	private int age;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpaw() {
		return upaw;
	}

	public void setUpaw(String upaw) {
		this.upaw = upaw;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
