package com.caipiao.dao;



import com.caipiao.pojo.User;

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


}