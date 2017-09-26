package com.caipiao.service;

import java.util.List;

import com.caipiao.pojo.RegistrationCode;

public interface RegistrationCodeService {
	/**
	 * 获取注册码
	 * @return
	 */
	String getData(Integer userId);
	
	/**
	 * 插入表中
	 * @param rc
	 * @return
	 */
	int insert(RegistrationCode rc);
	
	/**
	 * 根据count的值返回多条结果
	 * @param count
	 * @return
	 */
	List<String> getListData(Integer count);

}
