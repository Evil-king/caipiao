package com.caipiao.vo;


public class IceDataVO {
	private String openCode;
	private String opentime;

	public String getOpenCode() {
		return openCode;
	}

	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}
	
	
	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}

	@Override
	public String toString() {
		return "[openCode=" + openCode + ", opentime=" + opentime + "]";
	}
	
	
}
