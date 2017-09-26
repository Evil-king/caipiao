package com.caipiao.pojo;

import java.util.Date;


/**
 * 冷水数字
 * @author Administrator
 *
 */
public class ColdWaterDigital {
	private Integer id;
	private String name;
	/**
	 * 出现次数
	 */
	private Integer occurrences;
	private Date modifiedTime;
	private String expect;
	

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public ColdWaterDigital() {
		super();
	}
	
	public ColdWaterDigital(String name, Integer occurrences, String expect) {
		super();
		this.name = name;
		this.occurrences = occurrences;
		this.expect = expect;
	}
	

	@Override
	public String toString() {
		return "ColdWaterDigital [id=" + id + ", name=" + name + ", occurrences=" + occurrences + ", modifiedTime="
				+ modifiedTime + ", expect=" + expect + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(Integer occurrences) {
		this.occurrences = occurrences;
	}
	
	
}
