package com.caipiao.pojo;

import java.util.Date;

/**
 * 热水数字
 * @author Administrator
 *
 */
public class HotWaterDigital {
	private Integer id;
	/**
	 * 期号后两位
	 */
	private String expectTwoLast;
	/**
	 * 开奖号后两位
	 */
	private String openCodeTwoLast;
	private Date modifiedTime;
	
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public HotWaterDigital() {
		super();
	}

	public HotWaterDigital(String expectTwoLast, String openCodeTwoLast) {
		super();
		this.expectTwoLast = expectTwoLast;
		this.openCodeTwoLast = openCodeTwoLast;
	}

	@Override
	public String toString() {
		return "HotWaterDigital [id=" + id + ", expectTwoLast=" + expectTwoLast + ", openCodeTwoLast=" + openCodeTwoLast
				+ ", modifiedTime=" + modifiedTime + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpectTwoLast() {
		return expectTwoLast;
	}

	public void setExpectTwoLast(String expectTwoLast) {
		this.expectTwoLast = expectTwoLast;
	}

	public String getOpenCodeTwoLast() {
		return openCodeTwoLast;
	}

	public void setOpenCodeTwoLast(String openCodeTwoLast) {
		this.openCodeTwoLast = openCodeTwoLast;
	}

	
}
