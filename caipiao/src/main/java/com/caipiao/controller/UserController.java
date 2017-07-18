package com.caipiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caipiao.exception.BaseException;
import com.caipiao.pojo.User;
import com.caipiao.service.UserService;
import com.caipiao.vo.ServerResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value="user",method=RequestMethod.POST)
public class UserController{
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/login")
	public @ResponseBody ServerResponse<User> toLogin(String phone,String password){
		if(isBank(phone,password)) {
			throw new BaseException(1,"参数不能为空");
		}
		return ServerResponse.createBySuccess(0,"成功", userService.login(phone,password));
	}
	
	
	@RequestMapping(value="/register")
	public @ResponseBody ServerResponse<User> toRegister(User user){
		if(isBank(user.getPhone(),user.getPassword())) {
			throw new BaseException(1,"参数不能为空");
		}
		return ServerResponse.createBySuccess(0,"成功", userService.register(user));
		
	}
	
	
	/**
	 * 判断出入参数是否为空
	 * @param strs
	 * @return
	 */
	private boolean isBank(String ... strs) {
		boolean isBank = false;
		for(String str : strs) {
			isBank =(str == null || str.trim().isEmpty());
			if(isBank) {
				return true;
			}
		}
		return false;
	}
}
