package com.caipiao.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caipiao.exception.BaseException;
import com.caipiao.pojo.User;
import com.caipiao.service.UserService;
import com.caipiao.vo.ResultJson;
import com.caipiao.vo.ServerResponse;
import com.caipiao.vo.UserPage;
import com.caipiao.vo.UserVO;

@CrossOrigin(origins = "*", maxAge = 3600)//springmvc解决跨域问题
@Controller
@RequestMapping(value="user",method=RequestMethod.POST)
public class UserController{
	@Autowired
	private UserService userService;
	private Logger logg = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/login")
	public @ResponseBody ServerResponse<User> toLogin(String phone,String password){
		if(isBank(phone,password)) {
			throw new BaseException(1,"参数不能为空");
		}
		return ServerResponse.createBySuccess(0,"成功", userService.login(phone,password));
	}
	
	
	@RequestMapping(value="/register")
	public @ResponseBody ServerResponse<UserVO> toRegister(User user){
		if(isBank(user.getPhone(),user.getPassword())) {
			throw new BaseException(1,"参数不能为空");
		}
		return ServerResponse.createBySuccess(0,"成功", userService.register(user));
		
	}
	
	@RequestMapping(value="/updatePassword")
	public @ResponseBody ServerResponse<Integer> toUpdatePassword(String phone,String newPassword){
		if(isBank(phone,newPassword)) {
			throw new BaseException(1,"参数不能为空");
		}
		return ServerResponse.createBySuccess(0,"成功", userService.updatePassword(phone,newPassword));
	}
	
	@RequestMapping(value="/page")
	public @ResponseBody ServerResponse<ResultJson<User>> toPage(UserPage userPage){
		ResultJson<User> json = userService.toPage(userPage);
		if(json == null) {
			json = new ResultJson<User>();
		}
		return ServerResponse.createBySuccess(0,"成功", json);
	}
	
	@RequestMapping(value="/limit")
	public @ResponseBody ServerResponse<Integer> toLimit(String phone,String type){
		
		return ServerResponse.createBySuccess(0,"成功", userService.limitData(phone,type));
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
