package com.caipiao.dao;

import com.caipiao.pojo.Disclaimer;

public interface DisclaimerMapper {

	Disclaimer queryByParameter(Disclaimer dis);

	int insert(Disclaimer dis);

	int updateByParameter(Disclaimer dis);

	Disclaimer queryByUserId(Integer userId);

	
}