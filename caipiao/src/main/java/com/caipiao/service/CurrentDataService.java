package com.caipiao.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.caipiao.pojo.CurrentData;
import com.caipiao.vo.CurrentVO;

public interface CurrentDataService {
	/**
	 * 获取实时彩票数据
	 * @param timeout 
	 * @param useCert 
	 * @param url 
	 * @param xml 
	 * @return
	 */
	List<CurrentData> sendData(String phone,String token);
	
	/**
	 * 获取最冷数据
	 * @return
	 */
	Map<String, List<String>> getIcdData(Integer number);
	
	/**
	 * 获取最热数据
	 * @return
	 */
	Map<String, List<String>> getHotData();
	
	/**
	 * 一星玩法方案一
	 * @param str
	 * @return
	 */
	Map<String, Map<String, List<String>>> getStar();

	/**
	 * 定位
	 * @return
	 */
	Map<String, Map<String, List<String>>> positioning();
	
	/**
	 * 一星二星复制
	 */
	 Map<String,Object> getTwoStarCopy();
	 
	 /**
	  * 后三方法和复制
	  * @return
	  */
	 Map<String, Map<String, List<String>>> endThree(int countNum);

}

