package com.caipiao.service;

import com.caipiao.exception.BaseException;
import com.caipiao.pojo.User;

public interface UserService {
	/**
	 * 登录
	 * @param phone
	 * @param password
	 * @return
	 */
	User login(String phone, String password) throws BaseException;

	
	
	/**
	 * 注册
	 * @param phone
	 * @param password
	 * @param code
	 */
	User register(User user);
//	User register(String phone, String password, String code);
}
