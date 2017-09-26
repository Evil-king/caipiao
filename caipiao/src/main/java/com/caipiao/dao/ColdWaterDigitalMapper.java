package com.caipiao.dao;

import java.util.List;

import com.caipiao.pojo.ColdWaterDigital;

public interface ColdWaterDigitalMapper {
	
	/**
	 * 冷水数字存库
	 * @param coldWaterDigital
	 * @return
	 */
	int create(List<ColdWaterDigital> coldWaterDigital);
	
	List<ColdWaterDigital> selectAll();
	
	String getNewExpect();
}
