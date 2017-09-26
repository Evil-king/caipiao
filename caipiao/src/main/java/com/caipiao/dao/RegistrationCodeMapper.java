package com.caipiao.dao;

import java.util.List;

import com.caipiao.pojo.RegistrationCode;

public interface RegistrationCodeMapper {

	int insert(RegistrationCode rc);

	List<RegistrationCode> queryParameter(String registerCode);

	int updateParameter(RegistrationCode rcv);

	List<String> getListData(Integer count);
	
}