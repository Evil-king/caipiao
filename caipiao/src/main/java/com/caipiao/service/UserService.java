package com.caipiao.service;


import com.caipiao.exception.BaseException;
import com.caipiao.pojo.User;
import com.caipiao.vo.ResultJson;
import com.caipiao.vo.ResultPagination;
import com.caipiao.vo.UserPage;
import com.caipiao.vo.UserVO;

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
	UserVO register(User user);


	/**
	 * 更新密码
	 * @param userVO
	 * @return
	 */
	int updatePassword(String phone, String newPassword);


	/**
	 * 
	 * @param user
	 * @return
	 */
	User queryByParam(User user);



	int updateByParam(User user);


	/**
	 * page分页查询
	 * @return
	 */
	ResultJson<User> toPage(UserPage userPage);


	/**
	 * 启用和禁用
	 * @param phone
	 * @param type
	 * @return
	 */
	Integer limitData(String phone, String type);

}
