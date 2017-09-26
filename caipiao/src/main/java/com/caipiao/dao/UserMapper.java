package com.caipiao.dao;

import java.util.List;

import com.caipiao.pojo.User;
import com.caipiao.vo.ResultJson;
import com.caipiao.vo.ResultPagination;
import com.caipiao.vo.UserPage;

public interface UserMapper {
	
	/**
	 * 登录
	 * @param us
	 * @return
	 */
	int login(User us);

	/**
	 * 更新密码
	 * @param user
	 * @return
	 */
	int updateByPasswd(User user);

	/**
	 * 通过参数检查是否存在对象
	 * @param us
	 * @return
	 */
	User checkParameter(User us);

	/**
	 * 创建新用户
	 * @param user
	 * @return
	 */
	int create(User user);
	
	/**
	 * 根据手机号查询
	 * @param uu
	 * @return
	 */
	User checkByPhone(User uu);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	User queryByUserId(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	User queryByParam(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	int updateByParam(User user);
	
	/**
	 * page分页查询
	 * @return
	 */
	List<User> list(ResultPagination userPage);
	
	/**
	 * 统计
	 * @param userPage
	 * @return
	 */
	int total(ResultPagination userPage);
	

}