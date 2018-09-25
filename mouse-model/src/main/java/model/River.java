package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 
* @ClassName: River 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author shil
* @date 2018年9月15日 上午9:38:12 
*
 */
@Table(name = "solr_core_river")
public class River implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

	private String tybm;
	
	@Column(name = "riverName")
	private String riverName;

	public String getTybm() {
		return tybm;
	}

	public void setTybm(String tybm) {
		this.tybm = tybm;
	}

	public String getRiverName() {
		return riverName;
	}

	public void setRiverName(String riverName) {
		this.riverName = riverName;
	}
	
}
