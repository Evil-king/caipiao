package com.caipiao.pojo;

public class CurrentData {
    private Integer id;

    private String expect;

    private String openCode;

    private String openTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getOpenCode() {
		return openCode;
	}

	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}


	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Override
	public String toString() {
		return "CurrentData [id=" + id + ", expect=" + expect + ", openCode=" + openCode + ", openTime=" + openTime
				+ "]";
	}
	
   
}