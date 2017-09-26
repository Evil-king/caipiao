package com.caipiao.vo;

import com.caipiao.pojo.User;

public class UserPage extends ResultPagination{
	private User user;
	
	public User getUser(User user) {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
