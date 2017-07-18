package com.caipiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caipiao.dao.UserMapper;
import com.caipiao.exception.BaseException;
import com.caipiao.pojo.User;
import com.caipiao.service.UserService;
import com.caipiao.util.MD5;
import com.caipiao.util.StringUtil;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 登录
	 */
	public User login(String phone, String password) throws BaseException{
		 // 检测手机号码
        if (!StringUtil.isHandset(phone)) {
            throw new BaseException(1,"非法手机号码！");
        }
		User us = new User();
		us.setPhone(phone);
		us.setPassword(MD5.encryptMD5(password));
		User user = userMapper.checkByPhone(us);
		if(user == null){
			throw new BaseException(1,"手机号码或密码错误");
		}
		return user;
	}

	/**
	 * 注册
	 */
	public User register(User user) throws BaseException{
		//检验手机号
		if (!StringUtil.isHandset(user.getPhone())) {
			throw new BaseException(1,"非法手机号码！");
        }
		User uu = new User();
		uu.setPassword(MD5.encryptMD5(user.getPassword()));
		uu.setPhone(user.getPhone());
		uu.setCode(user.getCode());
		User us = userMapper.checkByPhone(uu);
		if(us != null){
			throw new BaseException(1,"账号已注册！");
		}
		//新增用户信息
			if(userMapper.create(user) < 0){
				throw new BaseException(1,"新增用户失败");
		}
        return user;
		
	}

}
