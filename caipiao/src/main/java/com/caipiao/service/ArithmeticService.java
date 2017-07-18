package com.caipiao.service;

import java.util.List;

public interface ArithmeticService {
	
	/**
	 * 首尾和
	 * @return
	 */
	List<String> sum(int num);
	/**
	 * 方程式
	 * @return
	 */
	List<String> equation();
	/**
	 * 胆组法
	 * @return
	 */
	List<String> group();
	/**
	 * 垃圾复式
	 * @return
	 */
	List<String> rubbish();

}
