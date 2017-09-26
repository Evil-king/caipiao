package com.caipiao.service;

import java.util.List;
import java.util.Map;

public interface MessageInfoService {
	/**
	 * 写入数据到信息板
	 * @param info
	 * @param userId
	 * @return
	 */
	Integer writerData(String title, String detail, String message, Integer userId,String type);
	
	/**
	 * 读取数据到信息板
	 * @param info
	 * @param userId
	 * @return
	 */
	List<Map<String,Object>> getData(Integer userId);
	
}
