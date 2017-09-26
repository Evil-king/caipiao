package com.caipiao.dao;

import com.caipiao.pojo.Notice;

public interface NoticeMapper {


	Notice queryByParameter(Notice notice);

	int insert(Notice notice);

	int updateByParameter(Notice notice);

	Notice queryByUserId(Integer userId);

	
}