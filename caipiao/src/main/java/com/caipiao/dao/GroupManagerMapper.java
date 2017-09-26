package com.caipiao.dao;

import com.caipiao.pojo.GroupManager;

public interface GroupManagerMapper {

	GroupManager queryByParameter(GroupManager groupManage);

	int insert(GroupManager groupManage);

	int updateByParameter(GroupManager groupManage);

	GroupManager queryByUserId(Integer userId);
	
}