package com.caipiao.vo;

import java.util.ArrayList;
import java.util.List;

import com.caipiao.pojo.CurrentData;


public class CaiPiaoGroup {
	private String code;//彩票码
	private String info;//信息
	private List<CurrentData> list = new ArrayList<>();//数据
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public List<CurrentData> getList() {
		return list;
	}
	public void setList(List<CurrentData> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "CaiPiaoGroup [code=" + code + ", info=" + info + ", list=" + list + "]";
	}
	
}
