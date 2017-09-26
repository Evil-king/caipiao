package com.caipiao.dao;

import java.util.List;

import com.caipiao.pojo.CurrentData;
import com.caipiao.vo.CurrentVO;

public interface CurrentDataMapper {
	
	/**
	 * 查询最新的一条
	 * @return
	 */
	CurrentData queryToDataOne(int num);
	
	/**
	 * 查询多条
	 * @return
	 */
	List<CurrentData> queryLimitMore(int num);
	
	
	/**
	 * 查询所有数据
	 * @return
	 */
	List<CurrentData> queryAll();
	
	
}