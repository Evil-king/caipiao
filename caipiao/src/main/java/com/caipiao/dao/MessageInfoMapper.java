package com.caipiao.dao;

import com.caipiao.pojo.MessageInfo;

public interface MessageInfoMapper {

	int insert(MessageInfo messageInfo);
	
	MessageInfo queryByParameter(MessageInfo messageInfo);

	int updateByParameter(MessageInfo messageInfo);

	MessageInfo queryByUserId(Integer userId);

}