package com.caipiao.service;

import java.util.List;
import java.util.Map;

public interface ArithmeticService {
	
	/**
	 * 四种玩法
	 * @return
	 */
	Map<String, Map<String, List<String>>> primacy();
	
	/**
	 * 带参数的四种玩法
	 * @param type
	 * @return
	 */
	Map<String, Map<String, List<String>>> primacyParam(String[] type);
	
	
	/**
	 * 不带方法标示
	 * @param type
	 * @return
	 */
	public Map<String, List<String>> primacyParamNo(String[] type);
	
	/**
	 * 通过参数查询前三 中三 后三的值
	 * @param type
	 * @param openCode
	 * @return
	 */
	public List<String> getPrizePrarm(String type, String openCode);
	
	/**
	 * 判断所有前三 中三 后三是否中奖
	 * @return
	 */
	public Map<String, Map<String, String>> getPrize();
}
